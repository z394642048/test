package com.chaoxing.test.service;

import java.util.List;
import java.util.Map;

public interface IFileUploadService {

    void addVideo(Map<String,String> map);

    List<String> getListName(String fileName);

}
