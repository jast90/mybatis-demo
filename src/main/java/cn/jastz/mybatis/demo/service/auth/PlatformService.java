package cn.jastz.mybatis.demo.service.auth;

import cn.jastz.mybatis.demo.dao.AuthPlatformMapper;
import cn.jastz.mybatis.demo.domain.PlatformAddForm;
import cn.jastz.mybatis.demo.entity.AuthPlatform;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageList;
import cn.jastz.page.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhiwen
 */
@Service
public class PlatformService {
    @Autowired
    private AuthPlatformMapper authPlatformMapper;

    public Page<AuthPlatform> queryPage(PageRequest pageRequest) {
        PageList<AuthPlatform> pageList = (PageList) authPlatformMapper.queryPage(pageRequest);
        return pageList.getPage();
    }

    public void save(PlatformAddForm platformAddForm) {
        authPlatformMapper.insert(platformAddForm.toAuthPlatform());
    }
}
