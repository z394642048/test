package com.chaoxing.test.mapper;

import com.chaoxing.test.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenAcaMapper {

    List<String> getFid();

}