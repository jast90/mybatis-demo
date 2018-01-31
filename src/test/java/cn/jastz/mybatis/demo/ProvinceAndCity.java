package cn.jastz.mybatis.demo;

import com.google.common.collect.Lists;
import me.jastz.common.china.district.City;

import java.util.List;

/**
 * @author zhiwen
 */
public class ProvinceAndCity {
    private City city;
    private List<City> children = Lists.newArrayList();


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<City> getChildren() {
        return children;
    }

    public void setChildren(List<City> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ProvinceAndCity{" +
                "city=" + city +
                ", children=" + children +
                '}';
    }
}
