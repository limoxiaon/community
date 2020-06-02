package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.service.CommentService;
import com.example.demo.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private  QuestionDTOService questionDTOService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id,
                            Model model){

        QuestionDTO questionDTO= questionDTOService.getById(id);
        List<CommentDTO> comments=commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //获取相关问题
        List<QuestionDTO> questionDTOS=commentService.selectByRelated(questionDTO);
        //追加阅读数
        questionDTOService.incViewCount(id);
        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",questionDTOS);
        return "question";
    }

}
