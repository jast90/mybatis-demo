package cn.jastz.mybatis.demo.controller.auth;

import cn.jastz.mybatis.demo.controller.BaseController;
import cn.jastz.mybatis.demo.dao.AuthBusinessMapper;
import cn.jastz.mybatis.demo.domain.OrganizationAddForm;
import cn.jastz.mybatis.demo.service.auth.OrganizationService;
import cn.jastz.page.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiwen
 */
@Controller
@RequestMapping("auth/organization")
public class OrganizationController extends BaseController<OrganizationService> {

    @Autowired
    private AuthBusinessMapper authBusinessMapper;

    @GetMapping("{pageNumber}")
    public String queryPage(@PathVariable("pageNumber") int pageNumber, Model model) {
        model.addAttribute("title", "组织列表");
        model.addAttribute("page", service.queryPage(PageRequest.of(pageNumber - 1, 15)));
        model.addAttribute("businessList", authBusinessMapper.selectAll());
        model.addAttribute("parentList", service.selectListByParentIsNull());
        return "auth/organization/list";
    }

    @PostMapping
    public String save(OrganizationAddForm organizationAddForm) {
        service.save(organizationAddForm);
        return "redirect:/auth/organization/1";
    }
}
