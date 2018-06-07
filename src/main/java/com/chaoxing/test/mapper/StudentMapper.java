package com.chaoxing.test.mapper;

import com.chaoxing.test.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKeyWithBLOBs(Student record);

    int updateByPrimaryKey(Student record);
}