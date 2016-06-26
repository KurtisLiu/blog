package com.lk.blog.service.impl;

import com.lk.blog.mapper.TagMapper;
import com.lk.blog.model.Tag;
import com.lk.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getAllTags() {
        List<Tag> tags = tagMapper.getAllTags();
        if (tags == null) {
            tags =  new ArrayList<Tag>();
        }
        return tags;
    }
}
