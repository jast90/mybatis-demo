package cn.jastz.mybatis.demo.dao;


import cn.jastz.page.domain.PageRequest;
import me.jastz.common.china.district.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityDao {

    void save(City city);

    void batchAdd(List<City> cityList);

    City queryById(int id);

    List<City> queryPage(@Param("pageRequest") PageRequest pageRequest, @Param("name") String name);
}
