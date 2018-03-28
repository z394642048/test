package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.MenuMapper;
import com.chaoxing.test.model.Menu;
import com.chaoxing.test.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByUid(Long uid) {
        List<Menu> menus = menuMapper.getMenuByUid(uid);
        return menus;
    }
}
