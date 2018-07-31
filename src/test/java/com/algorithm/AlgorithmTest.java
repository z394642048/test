package com.algorithm;

import com.chaoxing.test.model.Employee;
import org.junit.Test;

import java.util.ArrayList;

public class AlgorithmTest {

    @Test
    public void test1(){
        ArrayList<Employee> list1 = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setId(1L);
        e1.setRealName("xxx");
        e1.setTel("666666");
        Employee e2 = new Employee();
        e2.setId(2L);
        e2.setRealName("xxx");
        e2.setTel("55555555");
        Employee e3 = new Employee();
        e3.setId(2L);
        e3.setRealName("xxx");
        e3.setTel("77777777");
        Employee e4 = new Employee();
        e4.setId(1L);
        e4.setRealName("xxx");
        e4.setTel("888888888");
        list1.add(e1);
        list1.add(e2);
        list1.add(e3);
        list1.add(e4);





        ArrayList<Employee> list2 = new ArrayList<>();
        Employee e5 = new Employee();
        e5.setId(5L);
        e5.setRealName("xxx");
        e5.setTel("6666663333333");
        Employee e6 = new Employee();
        e6.setId(6L);
        e6.setRealName("xxx");
        e6.setTel("55555555444444444");
        Employee e7 = new Employee();
        e7.setId(7L);
        e7.setRealName("xxx");
        e7.setTel("777775555555555777");
        Employee e8 = new Employee();
        e8.setId(8L);
        e8.setRealName("xxx");
        e8.setTel("888888888");
        Employee e9 = new Employee();
        e9.setId(9L);
        e9.setRealName("xxx");
        e9.setTel("888888456456546888");
        list2.add(e5);
        list2.add(e6);
        list2.add(e7);
        list2.add(e8);
        list2.add(e9);
        for (Employee employee : list1) {
            if (list2.contains(employee)){
                System.out.println(list2.get(list2.indexOf(employee)));
                System.out.println(employee);
            }
        }
    }

    @Test
    public void test2(){

    }
}
