package cn.jastz.mybatis.demo.service.auth;

import cn.jastz.mybatis.demo.dao.AuthBusinessMapper;
import cn.jastz.mybatis.demo.domain.BusinessAddForm;
import cn.jastz.mybatis.demo.entity.AuthBusiness;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageList;
import cn.jastz.page.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhiwen
 */
@Service
public class BusinessService {
    @Autowired
    private AuthBusinessMapper authBusinessMapper;

    public Page<AuthBusiness> queryPage(PageRequest pageRequest) {
        PageList<AuthBusiness> pageList = (PageList) authBusinessMapper.queryPage(pageRequest);
        return pageList.getPage();
    }

    public void save(BusinessAddForm businessAddForm) {
        authBusinessMapper.insert(businessAddForm.toAuthBusiness());
    }
}
