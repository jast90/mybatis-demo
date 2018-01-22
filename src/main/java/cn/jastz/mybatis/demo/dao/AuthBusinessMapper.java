package cn.jastz.mybatis.demo.dao;

import cn.jastz.mybatis.demo.entity.AuthBusiness;
import java.util.List;

public interface AuthBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthBusiness record);

    AuthBusiness selectByPrimaryKey(Integer id);

    List<AuthBusiness> selectAll();

    int updateByPrimaryKey(AuthBusiness record);
}