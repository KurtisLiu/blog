package com.lk.blog.mapper;

import com.lk.blog.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    public List<Tag> getAllTags();
}
