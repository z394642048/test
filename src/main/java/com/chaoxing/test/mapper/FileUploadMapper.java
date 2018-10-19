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

    int addPltVideo(Map<String, Object> map);

    LinkedList<String> getListName(String fileName);

    List<HashMap<String, Object>> getNullUrl();

    void updateByUrl(@Param("id") Integer id, @Param("url") String url);

    Map<String, Object> getVideoName(String number);

    List<HashMap<String, Object>> getNoPass();

    void updateVideo(HashMap<String, Object> map);

    void addMiddleTable(@Param("id") int id, @Param("seriesId") int seriesId, @Param("sequence") int sequence);

    int addPltSeires(HashMap<String, Object> map);

    Map<String,Object> getVideoName10(String name);

    int addPltVideo10(Map<String, Object> map);
}
