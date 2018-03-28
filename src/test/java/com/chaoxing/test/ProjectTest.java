package com.chaoxing.test;

import com.chaoxing.test.mapper.EmployeeMapper;
import com.chaoxing.test.model.Employee;
import com.chaoxing.test.model.Menu;
import com.chaoxing.test.service.IFanyaUserDetailService;
import com.chaoxing.test.service.IMenuService;
import com.chaoxing.test.service.IOpenAcaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTest {


    @Autowired
    private IMenuService menuService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void index() {

        List<Menu> menuByUid = menuService.getMenuByUid((long) 1);
        System.out.println(menuByUid);
        List<Employee> employees = employeeMapper.getAll();
        System.out.println(employees);
    }



}
