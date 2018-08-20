package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.FileUploadMapper;
import com.chaoxing.test.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Override
    public void addVideo(Map<String,String> map) {
        fileUploadMapper.addVideo(map);
    }
}
