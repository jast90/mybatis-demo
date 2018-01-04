package cn.jastz.mybatis.demo.controller;

import cn.jastz.mybatis.demo.service.CityService;
import cn.jastz.page.domain.Page;
import cn.jastz.page.domain.PageRequest;
import me.jastz.common.china.district.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiwen
 */
@Controller
@RequestMapping("city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("page")
    public String queryPage(int pageNumber, int pageSize, Model model) {
        model.addAttribute("page", cityService.queryPage(PageRequest.of(pageNumber - 1, pageSize)));
        return "cityList";
    }
}
