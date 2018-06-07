package com.chaoxing.test;

import com.chaoxing.test.model.Student;
import com.chaoxing.test.service.IStudentService;
import com.chaoxing.test.util.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith( SpringRunner.class )
@SpringBootTest
public class FirstJedis {

    @Autowired
    private IStudentService studentService;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        Student student = studentService.selectByPrimaryKey(1);
        System.out.println(student);
    }

    @Test
    public void test2() {
        Student student = studentService.selectByPrimaryKey(1);
        System.out.println(student);
    }

    @Test
    public void test3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("==============");
//                    Student stu = studentService.selectByPrimaryKey(2);
//                    System.out.println(stu);
//                    System.out.println("=============="+Thread.currentThread().getId());
                    RedisLock lock = new RedisLock(redisTemplate, "lock_" + 1);
                    try {
                        if (lock.lock()) {
                            System.out.println("------------进锁");
                            Student student = studentService.selectByPrimaryKey(1);
                            System.out.println(student);
                            Thread.sleep(1000);
                            System.out.println("------------"+Thread.currentThread().getId());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("-----------关锁");
                        lock.unlock();
                    }
                }
            });
        }
        Thread.sleep(1111111);
    }
}
