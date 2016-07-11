package com.lk.blog.service.impl;

import com.lk.blog.exception.ParameterException;
import com.lk.blog.mapper.ReplyMapper;
import com.lk.blog.model.Reply;
import com.lk.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public void addReply(Reply reply) {
        if (reply.getContent() == "") {
            throw new ParameterException();
        }
        if (reply.getAuthor() == null) {
            throw new ParameterException();
        }
        if (reply.getTopic() == null) {
            throw new ParameterException();
        }
        Date now = new Date();
        reply.setCreateDate(now);
        reply.setUpdateDate(now);
        replyMapper.addReply(reply);
    }
}
