package cn.jastz.mybatis.demo;

import cn.jastz.mybatis.demo.dao.CityMapper;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageList;
import cn.jastz.page.domain.PageRequest;
import com.google.common.collect.Lists;
import me.jastz.common.china.district.City;
import me.jastz.common.china.district.ProvinceHandler;
import me.jastz.common.json.JsonUtil;
import me.jastz.common.kafka.KafkaUtil;
import me.jastz.common.kafka.stream.serdes.SerdesFactory;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
//@Ignore
public class MybatisDemoApplicationTests {
    @Autowired
    private CityMapper cityDao;

    @Test
    public void contextLoads() {
        System.out.println(cityDao);
    }

    @Test
    public void testSave() {
        City city = new City("上饶", "123456");
        cityDao.save(city);
        assertTrue(city.getId() > 0);
    }

    @Test
    public void testBatchAdd() {
        List<City> cityList = Lists.newArrayList();
        City city = new City("深圳", "1234567");
        cityList.add(city);
        cityList.add(new City("广州", "1234568"));
        cityDao.batchAdd(cityList);
    }

    @Test
    public void testQueryById() {
        City city = cityDao.queryById(1);
        assertTrue("北京市(京)".equals(city.getName()));
    }

    @Test
    public void testSaveAllCity() {
        List<City> cityList = ProvinceHandler.getAllCity();
        for (City city : cityList) {
            saveCity(city);
        }
    }

    private void saveCity(City city) {

        if (city.getChildren().isEmpty() == false) {
            cityDao.save(city);
            for (City item : city.getChildren()) {
                item.setParentId(city.getId());
                saveCity(item);
            }
        } else {
            cityDao.save(city);
        }
    }

    @Test
    public void queryPage() {
        List<City> cityPage = cityDao.queryPage(PageRequest.of(1, 30), "", null);
        if (cityPage instanceof Page) {
            Page page = (Page) cityPage;
            System.out.println(JsonUtil.objectToPrettyJson(page.getContent()));
        }
    }

    @Test
    public void testHashCode() {
        City city = cityDao.queryById(1);
        City city2 = cityDao.queryById(1);
        System.out.println(city.hashCode());
        System.out.println(city2.hashCode());
        assertTrue(city.equals(city2));
    }

    @Test
    public void cityTopicProducer() {
        PageRequest pageRequest = PageRequest.of(0, 30);
        PageList<City> pageList = (PageList<City>) cityDao.queryPage(pageRequest, "", null);
        if (CollectionUtils.isEmpty(pageList.getPage().getContent()) == false) {
            pageList.getPage().getContent().forEach(city -> KafkaUtil.producer(new Properties(), "cities", city.getName(), city));
        }
       /* while (pageList.getPage().hasNext()) {
            pageRequest = PageRequest.of((pageRequest.getPageNumber() + 1), 30);
            pageList = (PageList<City>) cityDao.queryPage(pageRequest, "", null);
            if (CollectionUtils.isEmpty(pageList.getPage().getContent()) == false) {
                pageList.getPage().getContent().forEach(city -> KafkaUtil.producer(new Properties(), "cities", city.getName(), city));
            }
        }*/
    }


    @Test
    public void provinceWithCities() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "provinceWithCities");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SerdesFactory.serdesFrom(City.class).getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, City> citiesStream = builder.stream("cities"
                , Consumed.with(Serdes.String(), SerdesFactory.serdesFrom(City.class)));

        KTable<String, ArrayList<City>> citiesByProvince = citiesStream.filter((name, city) -> city.getParentId() != 0)
                .groupBy((k, v) -> String.valueOf(v.getParentId())).aggregate(ArrayList::new, (k, v, a) -> {
                    a.add(v);
                    return a;
                });
        KStream<String, City> provinceStream = citiesStream
                .filter((name, city) -> city.getParentId() == 0);
        KTable<String, City> provinceWithCitiesTable = provinceStream.groupBy((k, v) -> String.valueOf(v.getId()))
                .reduce((a, b) -> a)
                .join(citiesByProvince, (province, cites) -> {
                    province.setChildren(cites);
                    return province;
                });
        provinceWithCitiesTable.toStream().to("provinceWithCity"
                , Produced.with(Serdes.String(), SerdesFactory.serdesFrom(City.class)));
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        final CountDownLatch latch = new CountDownLatch(1);

        // attach shutdown handler to catch control-c
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }

    @Test
    public void consumer() {
        KafkaUtil.consumer(new Properties(), City.class, "provinceWithCity");
    }

    @Test
    public void produceString() {
        KafkaUtil.producer(new Properties(), "strings", "key", "hello");
    }
}
