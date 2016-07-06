package com.lk.blog.mapper;

import com.lk.blog.dto.Page;
import com.lk.blog.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TopicMapper {
    
    public Topic getTopicById(String topicId);

    public void addTopic(Topic topic);
    
    public List<Topic> getTopicsByPage(Page page);

    public void updateTopic(Topic topic);

    public void deleteTopic(String topicId);

}
