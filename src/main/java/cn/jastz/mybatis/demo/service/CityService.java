package cn.jastz.mybatis.demo.service;

import cn.jastz.mybatis.demo.dao.CityMapper;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageList;
import cn.jastz.page.domain.PageRequest;
import me.jastz.common.china.district.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhiwen
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityDao;

    public Page<City> queryPage(PageRequest pageRequest) {
        PageList<City> pageList = (PageList) cityDao.queryPage(pageRequest, "");
        return pageList.getPage();
    }
}
