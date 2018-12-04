package com.chaoxing.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    //代理类中的真实对象
    private Object target;

    //构造函数，给我们的真实对象赋值
    public MyInvocationHandler(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        System.out.println("args:" + args);
        Object retVal = method.invoke(target, args);
        System.out.println("after invoke");
        return retVal;
    }
}