package cn.jastz.mybatis.demo.domain;

import java.math.BigDecimal;
import java.util.List;

public class City {
    /**
     * 地名
     */
    String name;

    /**
     * 驻地
     */
    String address;
    /**
     * 人口
     */
    BigDecimal population;
    /**
     * 面积
     */
    BigDecimal proportion;
    /**
     * 行政区划代码
     */
    String code;
    /**
     * 区号
     */
    String areaCode;
    /**
     * 邮编
     */
    String zip;

    List<me.jastz.common.china.district.City> children;

    private int parentId;

    private int id;

    public City() {
    }

    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public City(String name, String address, BigDecimal population, BigDecimal proportion, String code, String areaCode, String zip) {
        this.name = name;
        this.address = address;
        this.population = population;
        this.proportion = proportion;
        this.code = code;
        this.areaCode = areaCode;
        this.zip = zip;
    }

    public City(String name, String address, BigDecimal population, BigDecimal proportion, String code, String areaCode, String zip, List<me.jastz.common.china.district.City> children) {
        this.name = name;
        this.address = address;
        this.population = population;
        this.proportion = proportion;
        this.code = code;
        this.areaCode = areaCode;
        this.zip = zip;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPopulation() {
        return population;
    }

    public void setPopulation(BigDecimal population) {
        this.population = population;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<me.jastz.common.china.district.City> getChildren() {
        return children;
    }

    public void setChildren(List<me.jastz.common.china.district.City> children) {
        this.children = children;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
