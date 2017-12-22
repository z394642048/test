package com.chaoxing.test.service;

import com.chaoxing.test.model.User;

public interface IUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByLogin(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
