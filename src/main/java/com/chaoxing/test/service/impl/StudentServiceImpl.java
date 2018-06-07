package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.StudentMapper;
import com.chaoxing.test.model.Student;
import com.chaoxing.test.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @CacheEvict(value="student",key="'id_'+#id")
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    @CachePut(value="student.name",key="#record.getName()")
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    @CachePut(value="student.name",key="#record.getName()")
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    @Cacheable(value = "student.id",key="#id+'-select'")
    public Student selectByPrimaryKey(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }
}
