package cn.jastz.mybatis.demo.domain;

import cn.jastz.mybatis.demo.entity.AuthOrganization;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author zhiwen
 */
public class OrganizationAddForm {
    private int businessId;
    private String code;
    private String name;

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthOrganization toAuthOrganization() {
        AuthOrganization authOrganization = new AuthOrganization();
        BeanUtils.copyProperties(this, authOrganization);
        authOrganization.setEnable((byte) 1);
        authOrganization.setDelStatus((byte) 0);
        authOrganization.setCreatedTime(new Date());
        return authOrganization;
    }
}
