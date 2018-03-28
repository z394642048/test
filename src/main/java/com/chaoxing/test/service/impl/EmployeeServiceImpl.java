package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.EmployeeMapper;
import com.chaoxing.test.model.Employee;
import com.chaoxing.test.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = employeeMapper.getAll();
        return employees;
    }
}
