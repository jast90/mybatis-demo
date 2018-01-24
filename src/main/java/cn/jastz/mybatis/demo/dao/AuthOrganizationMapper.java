package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthOrganization;
import cn.jastz.page.domain.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthOrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthOrganization record);

    AuthOrganization selectByPrimaryKey(Integer id);

    List<AuthOrganization> selectAll();

    int updateByPrimaryKey(AuthOrganization record);

    List<AuthOrganization> selectPage(@Param("pageRequest") PageRequest pageRequest);

    List<AuthOrganization> selectListByParentIsNull();
}