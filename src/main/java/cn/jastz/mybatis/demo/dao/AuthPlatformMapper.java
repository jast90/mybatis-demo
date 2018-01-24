package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthPlatform;
import cn.jastz.page.domain.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthPlatformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthPlatform record);

    AuthPlatform selectByPrimaryKey(Integer id);

    List<AuthPlatform> selectAll();

    int updateByPrimaryKey(AuthPlatform record);

    List<AuthPlatform> queryPage(@Param("pageRequest") PageRequest pageRequest);
}