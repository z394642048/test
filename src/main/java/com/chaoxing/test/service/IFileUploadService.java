package com.chaoxing.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IFileUploadService {

    void addVideo(Map<String,String> map);

    List<String> getListName(String fileName);

    List<HashMap<String,Object>> getNullUrl();

    void updateByUrl(Integer id, String url);
}
