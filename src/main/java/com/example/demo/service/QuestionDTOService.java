package com.example.demo.service;

import com.example.demo.dto.PageDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.QuestionExtMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionExample;
import com.example.demo.model.User;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public  QuestionDTO getById(Long id) {
        QuestionDTO questionDTO=new QuestionDTO();
        Question question=questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public PageDTO list(Integer page, Integer size) {
        Integer offset=size*(page-1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_Create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS=new ArrayList<>();

        PageDTO<QuestionDTO> pageDTO=new PageDTO<>();

        for(Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setData(questionDTOS);
        Integer totalCount=(int)questionMapper.countByExample(new QuestionExample());
        pageDTO.setPageParam(totalCount,page,size);
        return pageDTO;
    }

    public PageDTO listByUser(Long id,Integer page, Integer size){
        Integer offset=size*(page-1);
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        PageDTO<QuestionDTO> pageDTO=new PageDTO();
        for(Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setData(questionDTOS);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        Integer totalCount=(int)questionMapper.countByExample(example);
        pageDTO.setPageParam(totalCount,page,size);
        return pageDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //执行插入操作
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setViewCount(0);
            questionMapper.insert(question);
        }else{
            //执行更新操作
            Question updateQuestion=new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setDescription(question.getDescription());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updater=questionMapper.updateByExampleSelective(updateQuestion, example);
            if(updater!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incViewCount(Long id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incViewCount(question);
    }
}
