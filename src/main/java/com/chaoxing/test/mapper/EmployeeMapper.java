package com.chaoxing.test.mapper;

import com.chaoxing.test.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {

    public List<Employee> getAll();
}
