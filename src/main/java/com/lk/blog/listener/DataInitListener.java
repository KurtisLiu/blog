package com.lk.blog.listener;

import com.lk.blog.constans.ApplicationConstants;
import com.lk.blog.model.Tag;
import com.lk.blog.service.TagService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class DataInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext cx = sce.getServletContext();
        TagService tagService = (TagService)WebApplicationContextUtils.getWebApplicationContext(cx).getBean("tagServiceImpl");
        List<Tag> tags = tagService.getAllTags();
        cx.setAttribute(ApplicationConstants.TAGS, tags);
        System.out.println(tags);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
