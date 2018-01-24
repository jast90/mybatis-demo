package cn.jastz.mybatis.demo.controller.auth;

import cn.jastz.mybatis.demo.dao.AuthBusinessMapper;
import cn.jastz.mybatis.demo.domain.PlatformAddForm;
import cn.jastz.mybatis.demo.service.auth.PlatformService;
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
@RequestMapping("auth/platform")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @Autowired
    private AuthBusinessMapper authBusinessMapper;

    @GetMapping("{pageNumber}")
    public String queryPage(@PathVariable("pageNumber") int pageNumber, Model model) {
        model.addAttribute("title", "平台列表");
        model.addAttribute("page", platformService.queryPage(PageRequest.of(pageNumber - 1, 15)));
        model.addAttribute("businessList", authBusinessMapper.selectAll());
        return "auth/platform/list";
    }

    @PostMapping
    public String save(PlatformAddForm platformAddForm) {
        platformService.save(platformAddForm);
        return "redirect:/auth/platform/1";
    }
}
