package com.lk.blog.service;

import java.util.List;

import com.lk.blog.dto.Page;
import com.lk.blog.model.Topic;

public interface TopicService {
    public List<Topic> getTopicsByPage(Page page);

    public void addTopic(Topic topic);

}
