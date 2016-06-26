package com.lk.blog.controller.ui;

import com.lk.blog.model.User;
import com.lk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/setting/{userId}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable String userId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", userService.getUserById(userId));
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String doRegister(User user) {
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/topic";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String doLogin(User user) {
        return "redirect:/topic";
    }

}
