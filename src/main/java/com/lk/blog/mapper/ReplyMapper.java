package com.lk.blog.mapper;

import com.lk.blog.model.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper {
    public void updateReply(Reply reply);

    public void addReply(Reply reply);

    public void deleteReply(String replyId);

    public List<Reply> getReplyByTopicId(String topicId);
}
