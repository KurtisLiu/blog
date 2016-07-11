package com.lk.blog.controller.ui;

import com.lk.blog.annotation.Login;
import com.lk.blog.constans.ApplicationConstants;
import com.lk.blog.model.Reply;
import com.lk.blog.model.User;
import com.lk.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Login
    @RequestMapping(method = RequestMethod.POST)
    public String addReply(HttpSession session, Reply reply) {
        User user = (User) session.getAttribute(ApplicationConstants.LOGIN_USER);
        reply.setAuthor(user);
        replyService.addReply(reply);
        return "redirect:/topic/" + reply.getTopic().getId();
    }
}
