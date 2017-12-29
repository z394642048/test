package com.chaoxing.test.controller;

import com.chaoxing.test.model.Student;
import com.chaoxing.test.service.IStudentService;
import com.chaoxing.test.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private RedisService redisService;

    @RequestMapping({"index","/",""})
    public String index(){
        Student student = new Student("阿萨德",15,"阿萨德巴金说多了氨基酸快点哈卡萨丁奥斯卡接电话");
        int insert = studentService.insert(student);
        System.out.println(insert);
        return "login";
    }

    //从redis获取某个用户
    @RequestMapping(value = "/getStudentfromredis", method = RequestMethod.GET)
    public @ResponseBody
    Student getRedis(@RequestParam String key) {
        return (Student)redisService.get(key);
    }
/*
    //获取所有用户
    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public @ResponseBody
    Page<Student> list(Model model, Pageable pageable){
        return StudentService.findAll(pageable);
    }*/

    //添加用户
    @GetMapping(value="/addStudent")
    public @ResponseBody String addStudent(@RequestParam String dictum,
                                        @RequestParam String password, @RequestParam String Studentname) {
        Student Student = new Student();
        redisService.set(Student.getId()+"", Student);
        return "Saved";
    }



}
