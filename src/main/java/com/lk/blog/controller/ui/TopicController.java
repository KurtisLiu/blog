package com.lk.blog.controller.ui;

import com.lk.blog.model.Topic;
import com.lk.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pagination() {
        List<Topic> topics = topicService.getPaginatedTopics(0, 20);
        ModelAndView modelAndView = new ModelAndView("topics");
        modelAndView.addObject("topics", topics);
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveTopic(Topic topic) {
        topicService.addTopic(topic);
        return "redirect:/topic";
    }

    @RequestMapping(value = "/toCreate", method = RequestMethod.GET)
    public ModelAndView toCraete() {
        ModelAndView modelAndView = new ModelAndView("topicCreate");
        return modelAndView;
    }
}
