package cn.jastz.mybatis.demo.controller;

import cn.jastz.mybatis.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhiwen
 */
public class BaseController<T extends BaseService> {
    @Autowired
    protected T service;

}
