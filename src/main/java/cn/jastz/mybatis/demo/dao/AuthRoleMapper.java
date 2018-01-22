package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthRole;
import java.util.List;

public interface AuthRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRole record);

    AuthRole selectByPrimaryKey(Integer id);

    List<AuthRole> selectAll();

    int updateByPrimaryKey(AuthRole record);
}