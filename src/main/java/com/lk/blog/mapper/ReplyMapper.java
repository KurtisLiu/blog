package com.lk.blog.mapper;

import com.lk.blog.model.Reply;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyMapper {
    public void updateReply(Reply reply);

    public void addReply(Reply reply);

    public void deleteReply(String replyId);
}
