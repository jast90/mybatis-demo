package cn.jastz.mybatis.demo;

/**
 * @author zhiwen
 */
public class ProvinceDiji {
    private int dijiId;
    private int provinceId;
    private String dijiName;
    private String provinceName;

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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public static ProvinceDiji toProvinceDiji(Province province, Diji diji) {
        ProvinceDiji provinceDiji = new ProvinceDiji();
        provinceDiji.setDijiId(diji.getDijiId());
        provinceDiji.setDijiName(diji.getDijiName());
        provinceDiji.setProvinceId(province.getProvinceId());
        provinceDiji.setProvinceName(province.getProvinceName());
        return provinceDiji;
    }
}
