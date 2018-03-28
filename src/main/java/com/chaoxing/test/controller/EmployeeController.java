package com.chaoxing.test.controller;

import com.chaoxing.test.model.Employee;
import com.chaoxing.test.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping("index")
    public String Index(Model model){
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees",employees);
        return "employee/employee";
    }
}
