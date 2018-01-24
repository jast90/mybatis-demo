package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthRole;
import cn.jastz.page.domain.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRole record);

    AuthRole selectByPrimaryKey(Integer id);

    List<AuthRole> selectAll();

    int updateByPrimaryKey(AuthRole record);

    List<AuthRole> queryPage(@Param("pageRequest") PageRequest pageRequest);
}