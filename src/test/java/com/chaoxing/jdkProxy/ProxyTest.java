package com.chaoxing.jdkProxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) {
		CustomInvocationHandler handler = new CustomInvocationHandler(new HelloWorldImpl());
		HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{HelloWorld.class}, handler);
		proxy.sayHello("dezhonger");
 
	}
}