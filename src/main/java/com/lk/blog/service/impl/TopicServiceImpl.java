package com.lk.blog.service.impl;

import com.lk.blog.dto.Page;
import com.lk.blog.exception.DataAccessException;
import com.lk.blog.exception.ParameterException;
import com.lk.blog.mapper.TopicMapper;
import com.lk.blog.model.Topic;
import com.lk.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public Page<Topic> getTopicsByPage(String query, int pageNow) {
        Page<Topic> page = new Page<>();
        page.setPageNow(pageNow);
        page.setQuery(query);
        List<Topic> topics = topicMapper.getTopicsByPage(page);
        page.setList(topics);
        return page;
    }

    @Override
    public void addTopic(Topic topic) {
        if (topic == null) {
            throw new ParameterException();
        }
        Date now = new Date();
        topic.setCreateDate(now);
        topic.setUpdateDate(now);
        topic.setLastReplyDate(now);
        try {
            topicMapper.addTopic(topic);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
