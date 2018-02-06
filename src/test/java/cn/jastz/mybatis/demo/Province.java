package cn.jastz.mybatis.demo;

import me.jastz.common.china.district.City;

/**
 * @author zhiwen
 */
public class Province {
    private int provinceId;
    private String provinceName;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public static Province toProvince(City city) {
        if (city.getParentId() != 0) {
            return null;
        }
        Province province = new Province();
        province.setProvinceId(city.getId());
        province.setProvinceName(city.getName());
        return province;
    }
}
