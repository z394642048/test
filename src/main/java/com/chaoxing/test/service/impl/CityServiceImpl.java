package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.CityMapper;
import com.chaoxing.test.model.City;
import com.chaoxing.test.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public void insertCityList(List<City> cityLists) {
        cityMapper.insertCityList(cityLists);
    }
}
