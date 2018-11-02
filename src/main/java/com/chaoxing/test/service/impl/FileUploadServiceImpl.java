package com.chaoxing.test.service.impl;

import com.chaoxing.test.mapper.FileUploadMapper;
import com.chaoxing.test.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Override
    public void addVideo(Map<String, String> map) {
        fileUploadMapper.addVideo(map);
    }

    @Override
    public int addPltVideo(Map<String, Object> map) {
        fileUploadMapper.addPltVideo(map);
        return Integer.parseInt(map.get("id").toString());
    }

    @Override
    public LinkedList<String> getListName(String fileName) {
        return fileUploadMapper.getListName(fileName);
    }

    @Override
    public List<HashMap<String, Object>> getNullUrl() {
        return fileUploadMapper.getNullUrl();
    }

    @Override
    public void updateByUrl(Integer id, String url) {
        fileUploadMapper.updateByUrl(id, url);
    }

    @Override
    public Map<String, Object> getVideoName(String number) {
        return fileUploadMapper.getVideoName(number);
    }

    @Override
    public List<HashMap<String, Object>> getNoPass() {
        return fileUploadMapper.getNoPass();
    }

    @Override
    public void updateVideo(HashMap<String, Object> map) {
        fileUploadMapper.updateVideo(map);
    }

    @Override
    public void addMiddleTable(int id, int seriesId, int sequence) {
        fileUploadMapper.addMiddleTable(id, seriesId, sequence);
    }

    @Override
    public int addPltSeires(HashMap<String, Object> map) {
        fileUploadMapper.addPltSeires(map);
        return Integer.parseInt(map.get("id").toString());
    }

    @Override
    public Map<String, Object> getVideoName10(String name) {
        return fileUploadMapper.getVideoName10(name);
    }

    @Override
    public int addPltVideo10(Map<String, Object> map) {
        fileUploadMapper.addPltVideo10(map);
        return Integer.parseInt(map.get("id").toString());
    }

    @Override
    public void addPltVideo11(LinkedList<HashMap<String, Object>> list) {
        fileUploadMapper.addPltVideo11(list);
    }

    @Override
    public Integer addClassify(HashMap<Object, Object> map) {
        fileUploadMapper.addClassify(map);
        return Integer.parseInt(map.get("id").toString());
    }

    @Override
    public void addIntoTempTable(LinkedList<HashMap<String, Object>> list) {
        fileUploadMapper.addIntoTempTable(list);
    }

    @Override
    public void addIntoTempTable2(LinkedList<HashMap<String, Object>> list) {
        fileUploadMapper.addIntoTempTable2(list);
    }
}
