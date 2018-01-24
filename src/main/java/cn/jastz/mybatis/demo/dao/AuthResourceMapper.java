package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthResource;
import cn.jastz.page.domain.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthResource record);

    AuthResource selectByPrimaryKey(Integer id);

    List<AuthResource> selectAll();

    int updateByPrimaryKey(AuthResource record);

    List<AuthResource> queryPage(@Param("pageRequest") PageRequest pageRequest);

    List<AuthResource> selectAllByParentIdIsNull();
}