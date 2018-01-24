package cn.jastz.mybatis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhiwen
 */
public class BaseService<T> {
    @Autowired
    protected T mapper;
}
