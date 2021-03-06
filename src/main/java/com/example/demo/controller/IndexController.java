package com.example.demo.controller;

import com.example.demo.dto.PageDTO;
import com.example.demo.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionDTOService questionDTOService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "5") Integer size,
                        @RequestParam(value = "search",required = false) String search){

        //有search时，获取对应页面的问题还有下面分页的一些参数。没有search则获取全部问题
        PageDTO pageDTO =questionDTOService.list(search,page,size);

        //添加参数到前端
        model.addAttribute("pageDTO",pageDTO);
        model.addAttribute("search",search);
        return "index";
    }
}
