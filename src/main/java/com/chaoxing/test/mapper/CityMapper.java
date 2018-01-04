package com.chaoxing.test.mapper;

import com.chaoxing.test.model.City;
import com.chaoxing.test.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper {

    void insertCityList(List<City> cityLists);

}