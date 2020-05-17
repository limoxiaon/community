package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private  QuestionDTOService questionDTOService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                            Model model){
        QuestionDTO questionDTO= questionDTOService.getById(id);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }

}
