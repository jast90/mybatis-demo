package cn.jastz.mybatis.demo.controller;

import cn.jastz.mybatis.demo.service.CityService;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageRequest;
import me.jastz.common.china.district.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiwen
 */
@Controller
@RequestMapping("city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("{pageNumber}")
    public String queryPage(@PathVariable("pageNumber") int pageNumber, Model model) {
        model.addAttribute("title", "城市列表");
        model.addAttribute("page", cityService.queryPage(PageRequest.of(pageNumber - 1, 15)));
        return "cityList";
    }
}
