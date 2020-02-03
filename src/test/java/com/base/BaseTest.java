package com.base;

import com.chaoxing.test.model.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class BaseTest {

    @Test
    public void test1(){
        User user = new User();
        user.setName("2323");
        user.setPassword("666666666");
        user.setId(1);
        User user1 = new User();
        BeanUtils.copyProperties(user,user1);
    }
}
