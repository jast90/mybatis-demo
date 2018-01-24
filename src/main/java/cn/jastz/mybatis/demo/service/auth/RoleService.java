package cn.jastz.mybatis.demo.service.auth;

import cn.jastz.mybatis.demo.dao.AuthRoleMapper;
import cn.jastz.mybatis.demo.domain.PlatformAddForm;
import cn.jastz.mybatis.demo.domain.RoleAddForm;
import cn.jastz.mybatis.demo.entity.AuthRole;
import cn.jastz.mybatis.demo.service.BaseService;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageList;
import cn.jastz.page.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

/**
 * @author zhiwen
 */
@Service
public class RoleService extends BaseService<AuthRoleMapper> {

    public Page<AuthRole> queryPage(PageRequest pageRequest) {
        PageList<AuthRole> pageList = (PageList) mapper.queryPage(pageRequest);
        return pageList.getPage();
    }

    public void save(RoleAddForm roleAddForm) {
        mapper.insert(roleAddForm.toAuthRole());
    }
}
