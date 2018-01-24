package cn.jastz.mybatis.demo.domain;

import cn.jastz.mybatis.demo.entity.AuthPlatform;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author zhiwen
 */
public class PlatformAddForm {
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

    public AuthPlatform toAuthPlatform() {
        AuthPlatform authPlatform = new AuthPlatform();
        BeanUtils.copyProperties(this, authPlatform);
        authPlatform.setEnable((byte) 1);
        authPlatform.setDelStatus((byte) 0);
        authPlatform.setCreatedTime(new Date());
        return authPlatform;
    }
}
