package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(){
        return "user/join";
    }
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserVo vo){
        userService.addUser(vo);
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
    public String joinsuccess(){
        return "user/joinsuccess";
    }
}
