package cn.jastz.mybatis.demo;

import me.jastz.common.china.district.City;

/**
 * @author zhiwen
 */
public class Diji {
    private int dijiId;
    private int provinceId;
    private String dijiName;

    public Diji() {
    }

    public Diji(String dijiName) {
        this.dijiName = dijiName;
    }

    public int getDijiId() {
        return dijiId;
    }

    public void setDijiId(int dijiId) {
        this.dijiId = dijiId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getDijiName() {
        return dijiName;
    }

    public void setDijiName(String dijiName) {
        this.dijiName = dijiName;
    }

    public static Diji toDiji(City city) {
        if (city.getParentId() == 0) {
            return null;
        }
        Diji diji = new Diji();
        diji.setDijiId(city.getId());
        diji.setDijiName(city.getName());
        diji.setProvinceId(city.getParentId());
        return diji;
    }

    @Override
    public String toString() {
        return "Diji{" +
                "dijiId=" + dijiId +
                ", provinceId=" + provinceId +
                ", dijiName='" + dijiName + '\'' +
                '}';
    }
}
