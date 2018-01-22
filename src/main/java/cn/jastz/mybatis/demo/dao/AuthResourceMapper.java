package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthResource;
import java.util.List;

public interface AuthResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthResource record);

    AuthResource selectByPrimaryKey(Integer id);

    List<AuthResource> selectAll();

    int updateByPrimaryKey(AuthResource record);
}