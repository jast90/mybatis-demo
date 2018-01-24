package cn.jastz.mybatis.demo.controller.auth;

import cn.jastz.mybatis.demo.controller.BaseController;
import cn.jastz.mybatis.demo.dao.AuthPlatformMapper;
import cn.jastz.mybatis.demo.domain.ResourceAddForm;
import cn.jastz.mybatis.demo.service.auth.ResourceService;
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
@RequestMapping("auth/resource")
public class ResourceController extends BaseController<ResourceService> {
    @Autowired
    private AuthPlatformMapper authPlatformMapper;

    @GetMapping("{pageNumber}")
    public String queryPage(@PathVariable("pageNumber") int pageNumber, Model model) {
        model.addAttribute("title", "角色列表");
        model.addAttribute("platformList", authPlatformMapper.selectAll());
        model.addAttribute("parentList", service.selectListByParentIdIs0());
        model.addAttribute("page", service.queryPage(PageRequest.of(pageNumber - 1, 15)));
        return "auth/resource/list";
    }

    @PostMapping
    public String save(ResourceAddForm resourceAddForm) {
        service.save(resourceAddForm);
        return "redirect:/auth/resource/1";
    }
}
