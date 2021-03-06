package cn.jastz.mybatis.demo.domain;

import cn.jastz.mybatis.demo.entity.AuthBusiness;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author zhiwen
 */
public class BusinessAddForm {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AuthBusiness toAuthBusiness() {
        AuthBusiness authBusiness = new AuthBusiness();
        BeanUtils.copyProperties(this, authBusiness);
        authBusiness.setEnable((byte) 1);
        authBusiness.setDelStatus((byte) 0);
        authBusiness.setCreatedTime(new Date());
        return authBusiness;
    }
}
