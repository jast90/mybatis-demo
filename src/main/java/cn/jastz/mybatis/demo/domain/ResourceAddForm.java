package cn.jastz.mybatis.demo.domain;

import cn.jastz.mybatis.demo.entity.AuthResource;
import cn.jastz.mybatis.demo.entity.AuthRole;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author zhiwen
 */
public class ResourceAddForm {
    private String name;
    private String code;
    private int platformId;

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

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public AuthResource toAuthResource() {
        AuthResource authResource = new AuthResource();
        BeanUtils.copyProperties(this, authResource);
        authResource.setEnable((byte) 1);
        authResource.setDelStatus((byte) 0);
        authResource.setCreatedTime(new Date());
        return authResource;
    }
}
