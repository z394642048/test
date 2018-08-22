package com.chaoxing.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FileUploadMapper {

    void addVideo(Map<String, String> map);

    LinkedList<String> getListName(String fileName);

    List<HashMap<String, Object>> getNullUrl();

    void updateByUrl(@Param("id") Integer id, @Param("url") String url);
}
