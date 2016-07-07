package com.lk.blog.controller.ui;

import com.lk.blog.annotation.Login;
import com.lk.blog.constans.ApplicationConstants;
import com.lk.blog.model.User;
import com.lk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
        String returnUrl = request.getParameter("returnUrl");
        return "redirect:" + (returnUrl == null ? "/topic" : returnUrl);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(ApplicationConstants.LOGIN_USER);
        return "redirect:/topic";
    }

    @Login
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public ModelAndView toSetting(HttpSession session) {
        User user = (User) session.getAttribute(ApplicationConstants.LOGIN_USER);
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("loginUser", user);
        return modelAndView;
    }

    @Login
    @RequestMapping(value = "")
    public ModelAndView updateUser(@RequestParam("avatorFile") CommonsMultipartFile file, User user, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = null;
        String fileType = file.getContentType();
        if (fileType.indexOf("image") == -1) {
            modelAndView = new ModelAndView("profile");
            modelAndView.addObject("error", "请上传一张图片");
            return modelAndView;
        }
        String fileName = file.getOriginalFilename();
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/build/upload");
        File targetFile = new File(path + "/" + fileName);
        file.transferTo(targetFile);

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(ApplicationConstants.LOGIN_USER);
        loginUser.setAvator("/build/upload/" + fileName);
        loginUser.setUsername(user.getUsername());
        loginUser.setEmail(user.getEmail());
        userService.updateUser(loginUser);

        modelAndView = new ModelAndView("redirect:/topic");
        return modelAndView;
    }

}
