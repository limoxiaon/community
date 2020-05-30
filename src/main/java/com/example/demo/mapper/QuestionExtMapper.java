package com.example.demo.mapper;

import com.example.demo.model.Question;

public interface QuestionExtMapper {
    int incViewCount(Question question);
    int incCommentCount(Question question);
}
