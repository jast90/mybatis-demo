package cn.jastz.mybatis.demo;

import cn.jastz.mybatis.demo.dao.CityDao;
import cn.jastz.mybatis.demo.domain.City;

import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import me.jastz.common.china.district.ProvinceHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


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
        assertTrue("上饶".equals(city.getName()));
    }

    @Test
    public void testSaveAllCity() {
        List<me.jastz.common.china.district.City> cityList = ProvinceHandler.getAllCity();
        for (me.jastz.common.china.district.City city : cityList) {
            saveCity(city);
        }
    }

    private void saveCity(me.jastz.common.china.district.City city) {
        if (city.getChildren().isEmpty()) {
//            City city1 = new City(), city2;
//            BeanUtils.copyProperties(city, city1);
//            cityDao.save(city1);
        } else {
            City city1 = new City(), city2;
            BeanUtils.copyProperties(city, city1);
            cityDao.save(city1);
            List<City> list = Lists.newArrayList();
            for (me.jastz.common.china.district.City item : city.getChildren()) {
                city2 = new City();
                BeanUtils.copyProperties(item, city2);
                city2.setParentId(city1.getId());
                list.add(city2);
                saveCity(item);
            }
            cityDao.batchAdd(list);
        }
    }

}
