package com.lk.blog.controller.ui;

import com.lk.blog.constans.ApplicationConstants;
import com.lk.blog.model.User;
import com.lk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/setting/{userId}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable String userId) {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", userService.getUserById(userId));
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String doRegister(User user, HttpSession session) {
        User userInDB = userService.saveUser(user);
        userInDB.setPassword(null);
        session.setAttribute(ApplicationConstants.LOGIN_USER, userInDB);
        return "redirect:/topic";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String doLogin(User user, HttpSession session, HttpServletRequest request) {
        User userInDB = userService.getUserByEmail(user.getEmail());
        if (userInDB == null || !userInDB.getPassword().equals(Base64.getEncoder().encodeToString(user.getPassword().getBytes()))) {
            // invalidate user
            request.setAttribute("errorMessage", "用户名或密码错误");
            return "login";
        }
        userInDB.setPassword(null);
        session.setAttribute(ApplicationConstants.LOGIN_USER, userInDB);
        return "redirect:/topic";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(ApplicationConstants.LOGIN_USER);
        return "redirect:/topic";
    }

}
