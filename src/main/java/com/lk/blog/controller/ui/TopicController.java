package com.lk.blog.controller.ui;

import com.lk.blog.annotation.Login;
import com.lk.blog.constans.ApplicationConstants;
import com.lk.blog.dto.Page;
import com.lk.blog.model.Topic;
import com.lk.blog.model.User;
import com.lk.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pagination() {
        Page page = new Page();
        page.setPageNow(0);
        List<Topic> topics = topicService.getTopicsByPage(page);
        ModelAndView modelAndView = new ModelAndView("topics");
        page.setList(topics);
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
}
