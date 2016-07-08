package com.lk.blog.controller.ui;

import com.lk.blog.annotation.Login;
import com.lk.blog.constans.ApplicationConstants;
import com.lk.blog.dto.Page;
import com.lk.blog.model.Topic;
import com.lk.blog.model.User;
import com.lk.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pagination(HttpServletRequest request) {
        String query = request.getParameter("query");
        String pageNowStr = request.getParameter("pageNow");
        int pageNow = 0;
        if (pageNowStr != null) {
            pageNow = Integer.parseInt(pageNowStr);
        }
        Page page = topicService.getTopicsByPage(query, pageNow);
        ModelAndView modelAndView = new ModelAndView("topics");
        modelAndView.addObject("topics", page);
        return modelAndView;
    }

    @Login
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveTopic(Topic topic, HttpSession session) {
        User loginUser = (User) session.getAttribute(ApplicationConstants.LOGIN_USER);
        topic.setAuthor(loginUser);
        topicService.addTopic(topic);
        return "redirect:/topic";
    }

    @Login
    @RequestMapping(value = "/toCreate", method = RequestMethod.GET)
    public ModelAndView toCraete() {
        ModelAndView modelAndView = new ModelAndView("topicCreate");
        return modelAndView;
    }

    @RequestMapping(value = "/{topicId}")
    public ModelAndView toTopicDetail(@PathVariable String topicId) {
        Topic topic = topicService.getTopicById(topicId);
        if (topic == null) {
            return new ModelAndView("redirect:/404");
        }
        ModelAndView modelAndView = new ModelAndView("topicDetail");
        modelAndView.addObject(topic);
        return modelAndView;
    }
}
