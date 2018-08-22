package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.FileUploadMapper;
import com.chaoxing.test.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Override
    public void addVideo(Map<String, String> map) {
        fileUploadMapper.addVideo(map);
    }

    @Override
    public LinkedList<String> getListName(String fileName) {
        return fileUploadMapper.getListName(fileName);
    }

    @Override
    public List<HashMap<String, Object>> getNullUrl() {
        return fileUploadMapper.getNullUrl();
    }

    @Override
    public void updateByUrl(Integer id, String url) {
        fileUploadMapper.updateByUrl(id, url);
    }
}
