package com.lk.blog.mapper;

import java.util.List;

import com.lk.blog.model.Topic;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMapper {
    
    public Topic getTopicById(String topicId);

    public void addTopic(Topic topic);
    
    public List<Topic> getTopicPagination(int offset, int limit);

    public void updateTopic(Topic topic);

    public void deleteTopic(String topicId);

}
