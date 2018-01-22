package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthOrganization;
import java.util.List;

public interface AuthOrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthOrganization record);

    AuthOrganization selectByPrimaryKey(Integer id);

    List<AuthOrganization> selectAll();

    int updateByPrimaryKey(AuthOrganization record);
}