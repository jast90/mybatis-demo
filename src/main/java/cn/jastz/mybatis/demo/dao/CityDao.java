package cn.jastz.mybatis.demo.dao;


import me.jastz.common.china.district.City;

import java.util.List;

public interface CityDao {

    void save(City city);

    void batchAdd(List<City> cityList);

    City queryById(int id);
}
