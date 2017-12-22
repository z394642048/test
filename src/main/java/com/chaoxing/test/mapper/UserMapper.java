package com.chaoxing.test.mapper;

import com.chaoxing.test.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByLogin(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}