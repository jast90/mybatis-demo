package cn.jastz.mybatis.demo.controller.auth;

import cn.jastz.mybatis.demo.domain.BusinessAddForm;
import cn.jastz.mybatis.demo.service.BusinessService;
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
@RequestMapping("auth/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("{pageNumber}")
    public String queryPage(@PathVariable("pageNumber") int pageNumber, Model model) {
        model.addAttribute("title", "业务列表");
        model.addAttribute("page", businessService.queryPage(PageRequest.of(pageNumber - 1, 15)));
        return "auth/business/list";
    }

    @PostMapping
    public String save(BusinessAddForm businessAddForm) {
        businessService.save(businessAddForm);
        return "redirect:/auth/business/1";
    }
}
