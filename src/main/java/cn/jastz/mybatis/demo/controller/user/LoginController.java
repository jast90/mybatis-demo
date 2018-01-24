package cn.jastz.mybatis.demo.controller.user;

import cn.jastz.mybatis.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhiwen
 */
@Controller
@RequestMapping("user/login")
public class LoginController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping
    public String login(String username, String password) {
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("currentUserId", username);
        userService.login(username, password, session);
        return "redirect:/";
    }

    @GetMapping
    public String login() {
        return "/user/login";
    }
}
