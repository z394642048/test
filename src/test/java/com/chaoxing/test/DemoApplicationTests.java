package com.chaoxing.test;

import com.chaoxing.test.model.Student;
import com.chaoxing.test.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private RedisService redisService;

	@Test
	public void contextLoads() {

		Student student = new Student();
		student.setName("回收电话");
		student.setIntroduce("kjhsdkjfhsdkj交电话费广阔的房价");
		student.setAge(18);
		student.setId(1);
		Student student1 = new Student(2,"苟富贵",23,"sdfsdf法规的非官方个地方根深蒂固");
		Student student2 = new Student(3,"苟富2323贵",22133,"123213法规的非官方123地方根深蒂固");
        ArrayList<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        list.add(student2);
        System.out.println("=============================================================");
//		redisService.set(student.getId()+"",student);
		redisService.set("list",list);
        ArrayList<Student> list1 = (ArrayList)redisService.get("list");
		System.out.println(list1);
		System.out.println("=============================================================");
		redisService.setNX("miaosha","1");
		if (redisService.setNX("miaosha","1")){

        }
	}

}
