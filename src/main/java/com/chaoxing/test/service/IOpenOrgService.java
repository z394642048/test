package com.chaoxing.test.service;

import java.util.List;

public interface IOpenOrgService {

    List<String> getFid();


    /**
     * 把机构插入对应的区域中
     *
     * @param orgNames
     * @param cityCode
     */
    public void insertAll(String orgNames, String cityCode);

    void insertCity(String orgNames, String cityCode);
}
