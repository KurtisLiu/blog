package com.lk.blog.service.impl;

import com.lk.blog.mapper.TopicMapper;
import com.lk.blog.model.Topic;
import com.lk.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> getPaginatedTopics(int offset, int size) {
        List<Topic> topics = topicMapper.getTopicPagination(offset, size);
        return topics;
    }

    @Override
    public void addTopic(Topic topic) {
        topicMapper.addTopic(topic);
    }
}
