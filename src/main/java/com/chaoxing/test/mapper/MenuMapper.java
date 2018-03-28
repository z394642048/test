package com.chaoxing.test.mapper;

import com.chaoxing.test.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    public List<Menu> getMenuByUid(Long uid);
}
