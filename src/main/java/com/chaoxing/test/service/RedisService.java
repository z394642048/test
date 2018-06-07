package com.chaoxing.test.service;

public interface RedisService {

    public void set(String key, Object value);

    public Object get(String key);

    public Boolean setNX(String key, String value);
}
