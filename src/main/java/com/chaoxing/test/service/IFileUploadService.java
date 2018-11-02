package com.chaoxing.test.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface IFileUploadService {

    void addVideo(Map<String, String> map);

    int addPltVideo(Map<String, Object> map);

    List<String> getListName(String fileName);

    List<HashMap<String, Object>> getNullUrl();

    void updateByUrl(Integer id, String url);

    Map<String, Object> getVideoName(String number);

    List<HashMap<String, Object>> getNoPass();

    void updateVideo(HashMap<String, Object> map);

    void addMiddleTable(int id, int seriesId, int sequence);

    int addPltSeires(HashMap<String, Object> map);

    Map<String,Object> getVideoName10(String name);

    int addPltVideo10(Map<String, Object> map);

    void addPltVideo11(LinkedList<HashMap<String, Object>> list);

    Integer addClassify(HashMap<Object, Object> map);

    void addIntoTempTable(LinkedList<HashMap<String,Object>> list);

    void addIntoTempTable2(LinkedList<HashMap<String,Object>> list);
}
