package com.chaoxing.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FanyaUserDetailMapper {
    void updateAid(List<String> fids);
}
