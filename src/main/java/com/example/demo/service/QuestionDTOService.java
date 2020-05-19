package com.example.demo.service;

import com.example.demo.dto.PageDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionDTOService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public  QuestionDTO getById(Integer id) {
        QuestionDTO questionDTO=new QuestionDTO();
        Question question=questionMapper.getById(id);
        User user=userMapper.findById(question.getCreator());
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public PageDTO list(Integer page, Integer size) {
        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        PageDTO pageDTO=new PageDTO();
        for(Question question:questions){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestionDTOS(questionDTOS);
        Integer totalCount=questionMapper.total();
        pageDTO.setPageParam(totalCount,page,size);
        return pageDTO;
    }

    public PageDTO listByUser(Integer id,Integer page, Integer size){
        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.listByUser(id,offset,size);
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        PageDTO pageDTO=new PageDTO();
        for(Question question:questions){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestionDTOS(questionDTOS);
        Integer totalCount=questionMapper.totalUser(id);
        pageDTO.setPageParam(totalCount,page,size);
        return pageDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //执行插入操作
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //执行更新操作
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
