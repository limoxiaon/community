package com.example.demo.mapper;

import com.example.demo.dto.QuestionQueryDTO;
import com.example.demo.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incViewCount(Question question);
    int incCommentCount(Question question);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
