package cn.jastz.mybatis.demo.service.auth;

import cn.jastz.mybatis.demo.dao.AuthResourceMapper;
import cn.jastz.mybatis.demo.domain.ResourceAddForm;
import cn.jastz.mybatis.demo.entity.AuthResource;
import cn.jastz.mybatis.demo.service.BaseService;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageList;
import cn.jastz.page.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhiwen
 */
@Service
public class ResourceService extends BaseService<AuthResourceMapper> {
    public Page<AuthResource> queryPage(PageRequest pageRequest) {
        PageList<AuthResource> pageList = (PageList) mapper.queryPage(pageRequest);
        return pageList.getPage();
    }

    public void save(ResourceAddForm resourceAddForm) {
        mapper.insert(resourceAddForm.toAuthResource());
    }

    public List<AuthResource> selectListByParentIdIs0() {
        return mapper.selectAllByParentIdIsNull();
    }
}
