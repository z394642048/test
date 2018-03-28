package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.OpenAcaMapper;
import com.chaoxing.test.service.IOpenAcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAcaServiceImpl implements IOpenAcaService {

    @Autowired
    private OpenAcaMapper openAcaMapper;

    @Override
    public List<String> getFid() {
        return openAcaMapper.getFid();
    }
}
