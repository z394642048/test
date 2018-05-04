package com.chaoxing.test.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenOrgMapper {

    List<String> getFid();

    void insertAll(@Param("orgNames")String orgNames, @Param("cityCode")String cityCode);

    void insertCity(@Param("orgNames")String orgNames, @Param("cityCode")String cityCode);

}