package com.lk.blog.service;

import com.lk.blog.dto.Page;
import com.lk.blog.model.Topic;

public interface TopicService {
    public Page<Topic> getTopicsByPage(String query, int pageNow);

    public void addTopic(Topic topic);

    public Topic getTopicById(String topicId);
}
