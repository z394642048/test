package com.chaoxing.test.controller;

import com.chaoxing.test.model.Menu;
import com.chaoxing.test.model.User;
import com.chaoxing.test.service.IMenuService;
import com.chaoxing.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    IUserService userService;

    @Autowired
    private IMenuService menuService;

    @RequestMapping({"","/"})
    public String index(){
        return "login";
    }

    @RequestMapping("login")
    public String login(User user,Model model){
        User loginUser = userService.selectByLogin(user);
        System.out.println(loginUser);
        List<Menu> menus = menuService.getMenuByUid((long)loginUser.getId());
        model.addAttribute("menus",menus);
        model.addAttribute("loginUser",loginUser);
        return "index";
    }

}
