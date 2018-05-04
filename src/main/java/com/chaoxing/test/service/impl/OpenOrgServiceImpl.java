package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.OpenOrgMapper;
import com.chaoxing.test.service.IOpenOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: leolin
 * Date: 2017/6/28_17:39
 * version:
 * description:
 */
@Service
public class OpenOrgServiceImpl implements IOpenOrgService {


    @Autowired
    private OpenOrgMapper openOrgMapper;

    @Override
    public List<String> getFid() {
        return null;
    }

    @Override
    public void insertAll(String orgNames,String cityCode) {
        openOrgMapper.insertAll(orgNames,cityCode);
    }

    @Override
    public void insertCity(String orgNames, String cityCode) {
        openOrgMapper.insertCity(orgNames,cityCode);
    }
}