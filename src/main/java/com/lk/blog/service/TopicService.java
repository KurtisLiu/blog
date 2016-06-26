package com.lk.blog.service;

import java.util.List;
import com.lk.blog.model.Topic;

public interface TopicService {
    public List<Topic> getPaginatedTopics(int offset, int size);

    public void addTopic(Topic topic);

}
