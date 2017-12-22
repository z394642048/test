package com.chaoxing.test.controller;

import com.chaoxing.test.model.User;
import com.chaoxing.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    IUserService userService;

    @RequestMapping({"","/"})
    public String index(){
        return "login";
    }

    @RequestMapping("login")
    public String login(User user){
        User login = userService.selectByLogin(user);
        System.out.println(login);
        return "main";
    }

}
