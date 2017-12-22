package com.chaoxing.test.controller;

import com.chaoxing.test.model.Student;
import com.chaoxing.test.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping({"index","/",""})
    public String index(){
        Student student = new Student("阿萨德",15,"阿萨德巴金说多了氨基酸快点哈卡萨丁奥斯卡接电话");
        int insert = studentService.insert(student);
        System.out.println(insert);
        return "login";
    }
}
