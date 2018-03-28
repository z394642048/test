package com.chaoxing.test.service;

import com.chaoxing.test.model.Menu;

import java.util.List;

public interface IMenuService {
    public List<Menu> getMenuByUid(Long uid);
}
