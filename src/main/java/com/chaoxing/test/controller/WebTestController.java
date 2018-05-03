package com.chaoxing.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class WebTestController {

    @RequestMapping("test1")
    public String test1(){

        return "test/test1";
    }
}
