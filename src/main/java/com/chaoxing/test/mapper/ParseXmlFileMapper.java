package com.chaoxing.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ParseXmlFileMapper {
    void parse2(List list);

    void parse3(List list);

    void parse4(List list);
}
