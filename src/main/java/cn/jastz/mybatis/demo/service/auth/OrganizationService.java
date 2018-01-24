package cn.jastz.mybatis.demo.service.auth;

import cn.jastz.mybatis.demo.dao.AuthOrganizationMapper;
import cn.jastz.mybatis.demo.domain.OrganizationAddForm;
import cn.jastz.mybatis.demo.entity.AuthOrganization;
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
public class OrganizationService extends BaseService<AuthOrganizationMapper> {

    public Page<AuthOrganization> queryPage(PageRequest pageRequest) {
        PageList<AuthOrganization> pageList = (PageList) mapper.selectPage(pageRequest);
        return pageList.getPage();
    }

    public void save(OrganizationAddForm organizationAddForm) {
        mapper.insert(organizationAddForm.toAuthOrganization());
    }

    public List<AuthOrganization> selectListByParentIsNull() {
        return mapper.selectListByParentIsNull();
    }
}
