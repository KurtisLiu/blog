package com.lk.blog.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("")
    public String index() {
        return "forward:/topic";
    }

    @RequestMapping("/404")
    public String notFound() {
        return "exception/404";
    }
}
