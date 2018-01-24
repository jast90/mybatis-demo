package cn.jastz.mybatis.demo.domain;

import cn.jastz.mybatis.demo.entity.AuthRole;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author zhiwen
 */
public class RoleAddForm {
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

    public AuthRole toAuthRole() {
        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(this, authRole);
        authRole.setEnable((byte) 1);
        authRole.setDelStatus((byte) 0);
        authRole.setCreatedTime(new Date());
        return authRole;
    }
}
