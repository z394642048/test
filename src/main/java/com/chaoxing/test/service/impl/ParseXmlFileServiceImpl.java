package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.ParseXmlFileMapper;
import com.chaoxing.test.service.IParseXmlFileService;
import com.chaoxing.test.util.ParseXmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ParseXmlFileServiceImpl implements IParseXmlFileService {

    @Autowired
    private ParseXmlFileMapper parseXmlFileMapper;


    @Override
    public void parse2(List list) {
        parseXmlFileMapper.parse2(list);
    }

    @Override
    public void parse3(List list) {
        parseXmlFileMapper.parse3(list);
    }

    @Override
    public void parse4(List list) {
        parseXmlFileMapper.parse4(list);
    }
}
