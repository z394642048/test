package com.chaoxing.jdkProxy;

public class HelloWorldImpl implements HelloWorld {

    @Override
    public String sayHello(String name) {
        System.out.println("真实对象： " + name);
        return "返回值:" + name + "呵呵哒";
    }

}