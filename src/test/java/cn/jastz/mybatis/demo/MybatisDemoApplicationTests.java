package cn.jastz.mybatis.demo;

import cn.jastz.mybatis.demo.dao.CityDao;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageRequest;
import cn.jastz.page.mybatis.PageResultHandler;
import com.google.common.collect.Lists;
import me.jastz.common.china.district.City;
import me.jastz.common.china.district.ProvinceHandler;
import me.jastz.common.json.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {
    @Autowired
    private CityDao cityDao;

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
        List<City> cityPage = cityDao.queryPage(PageRequest.of(1, 30), "");
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
}
