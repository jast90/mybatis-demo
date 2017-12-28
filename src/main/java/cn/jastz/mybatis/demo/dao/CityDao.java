package cn.jastz.mybatis.demo.dao;


import cn.jastz.mybatis.demo.domain.City;

import java.util.List;

public interface CityDao {

    void save(City city);

    void batchAdd(List<City> cityList);

    City queryById(int id);
}
