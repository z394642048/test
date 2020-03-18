package com.chaoxing.test;

import com.chaoxing.test.config.RedisLock;
import com.chaoxing.test.model.Student;
import com.chaoxing.test.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith( SpringRunner.class )
@SpringBootTest
public class FirstJedis {

    @Autowired
    private IStudentService studentService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private TaskExecutor taskExecutor;

    @Test
    public void test1() {
        Student student = studentService.selectByPrimaryKey(1);
        System.out.println(student);
    }

    @Test
    public void test2() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (valueOperations.setIfAbsent("3", "3")) {
            Student student = studentService.selectByPrimaryKey(1);
            System.out.println(student);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    ValueOperations valueOperations1 = redisTemplate.opsForValue();
                    if (valueOperations1.setIfAbsent("3", "3")) {
                        redisTemplate.expire("3", 30, TimeUnit.SECONDS);
                        try {
                            System.out.println("------------进锁" + Thread.currentThread().getId());
                            Student student = studentService.selectByPrimaryKey(1);
                            System.out.println(student);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("-----------关锁" + Thread.currentThread().getId());
                            redisTemplate.delete("3");
                        }
                    }
                }
            });
        }
    }


    @Test
    public void test3() throws InterruptedException {
//        JedisConnection jedisConnection = new JedisConnection();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("==============");
//                    Student stu = studentService.selectByPrimaryKey(2);
//                    System.out.println(stu);
//                    System.out.println("=============="+Thread.currentThread().getId());
                    RedisLock lock = new RedisLock(redisTemplate, "lock_" + 6);
                    try {
                        if (lock.lock()) {
                            System.out.println("------------进锁" + Thread.currentThread().getId());
                            Student student = studentService.selectByPrimaryKey(1);
                            System.out.println(student);
//                            Thread.sleep(10000);
//                            System.out.println("------------" + Thread.currentThread().getId());
                        } else {
                            System.out.println("==========没有进锁：" + Thread.currentThread().getId());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("-----------关锁" + Thread.currentThread().getId());
                        lock.unlock();
                    }
                }
            });
        }
        Thread.sleep(30 * 1000);
    }
}
