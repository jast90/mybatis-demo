package cn.jastz.mybatis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiwen
 */
@Controller
public class IndexController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title","首页");
        return "index";
    }
}
