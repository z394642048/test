package com.chaoxing.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
 
	private Object target;
 
	public CustomInvocationHandler(Object object) {
		this.target = object;
	}
 
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before");
		Object retVal = method.invoke(target, args);
		System.out.println("after");
		return retVal;
	}
}