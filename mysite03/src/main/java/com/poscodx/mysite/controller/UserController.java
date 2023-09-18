package com.poscodx.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.annotation.WebServlet;

@Controller
@WebServlet("/user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/join", method=RequestMethod.GET)
    public String join() {
        return "user/join";
    }

    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String join(UserVo vo) {
        userService.addUser(vo);
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
    public String joinsuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public String auth(
            @RequestParam(value = "email", required = true, defaultValue = "") String email,
            @RequestParam(value = "password", required = true, defaultValue = "") String password,
            Model model) {
        UserVo authUser = userService.getUser(email, password);
        if (authUser == null) {
            model.addAttribute("email",email);
            return "user/login";
        }

        return "user/login";
    }

}