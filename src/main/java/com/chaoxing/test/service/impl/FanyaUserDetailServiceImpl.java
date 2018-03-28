package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.FanyaUserDetailMapper;
import com.chaoxing.test.service.IFanyaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FanyaUserDetailServiceImpl implements IFanyaUserDetailService {

    @Autowired
    private FanyaUserDetailMapper fanyaUserDetailMapper;


    @Override
    public void updateAid(List<String> fids) {
        fanyaUserDetailMapper.updateAid(fids);
    }
}
