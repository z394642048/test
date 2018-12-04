package com.chaoxing.jdkProxy;

public class HelloWorldImpl implements HelloWorld {
	@Override
	public void sayHello(String name) {
		System.out.println("hello " + name);
	}
}