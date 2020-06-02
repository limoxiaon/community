package com.example.demo.controller;

import com.example.demo.dto.PageDTO;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.service.NotificationService;
import com.example.demo.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionDTOService questionDTOService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String section,
                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model){
        User user=(User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        if("questions".equals(section)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我关注的问题");
            Integer id=Integer.valueOf(user.getAccountId());
            PageDTO pageDTO =questionDTOService.listByUser(user.getId(),page,size);
            model.addAttribute("pageDTO",pageDTO);
        }else if("replies".equals(section)){
            PageDTO pageDTO =notificationService.list(user.getId(),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("pageDTO",pageDTO);
        }
        return "profile";
    }
}
