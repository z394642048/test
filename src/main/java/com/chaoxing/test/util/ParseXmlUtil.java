package com.chaoxing.test.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseXmlUtil {

    public static List<HashMap<String, Object>> parse(String filePath) {
        try {
            File file = ResourceUtils.getFile(filePath);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();

            //解析type-set下的节点，第二级分类
            Element element = (Element) root.elements("type-set").get(0);
            List<Element> typeSetElements = element.elements();
            LinkedList<HashMap<String, Object>> list = new LinkedList<>();
            for (Element childFirst : typeSetElements) {
                HashMap<String, Object> map = new HashMap<>();
//                map.put("id", childFirst.attributeValue("id"));
                map.put("sequence", childFirst.attributeValue("order"));
                map.put("name", childFirst.attributeValue("name"));
//                map.put("dynasty", childFirst.attributeValue("dynasty"));
//                map.put("pattern", childFirst.attributeValue("pattern"));
                Element desElement = (Element) childFirst.elements("description").get(0);
                map.put("intro", desElement.getText().replaceAll("\t", "").replaceAll("\n", ""));
                //每一系列装入对应的信息
                Element child = (Element) root.elements("resource-set").get(0);
                List<Element> childList = child.elements();
                LinkedList<HashMap<String, Object>> listChild = new LinkedList<>();
                int i = 0;
                for (Element element1 : childList) {
                    boolean tem = false;
                    Pattern pattern = Pattern.compile(childFirst.attributeValue("pattern"));
                    Matcher matcher = pattern.matcher(element1.attributeValue("id"));
                    tem = matcher.matches();
                    if (tem) {
                        HashMap<String, Object> map1 = new HashMap<>();
//                        map1.put("id", element1.attributeValue("id"));
                        map1.put("sequence", element1.attributeValue("order"));
                        map1.put("name", element1.attributeValue("name"));
                        map1.put("path", element1.attributeValue("path"));
                        listChild.add(map1);
                        i++;
                    }
                }
                map.put("episode", i);
                map.put("children", listChild);
                list.add(map);
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询type-set下的节点
     * @param filePath
     * @return
     */
    public static List<HashMap<String, Object>> parse2(String filePath) {
        try {
            File file = ResourceUtils.getFile(filePath);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();

            //解析type-set下的节点，第二级分类
            Element element = (Element) root.elements("type-set").get(0);
            List<Element> typeSetElements = element.elements();
            LinkedList<HashMap<String, Object>> list = new LinkedList<>();
            for (Element childFirst : typeSetElements) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", childFirst.attributeValue("id"));
                map.put("order", childFirst.attributeValue("order"));
                map.put("name", childFirst.attributeValue("name"));
                map.put("dynasty", childFirst.attributeValue("dynasty"));
                map.put("pattern", childFirst.attributeValue("pattern"));
                Element desElement = (Element) childFirst.elements("description").get(0);
                map.put("intro", desElement.getText().replaceAll("\t", "").replaceAll("\n", "").trim());
                list.add(map);
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询resource-set下的节点
     * @param filePath
     * @return
     */
    public static List<HashMap<String, Object>> parse3(String filePath) {
        try {
            File file = ResourceUtils.getFile(filePath);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            //解析resource-set下的节点，第二级分类
            Element element = (Element) root.elements("resource-set").get(0);
            List<Element> typeSetElements = element.elements();
            LinkedList<HashMap<String, Object>> list = new LinkedList<>();
            for (Element childFirst : typeSetElements) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", childFirst.attributeValue("id"));
                map.put("order", childFirst.attributeValue("order"));
                map.put("name", childFirst.attributeValue("name"));
                map.put("path", childFirst.attributeValue("path"));
                list.add(map);
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询display节点
     * @param filePath
     * @return
     */
    public static List<HashMap<String, Object>> parse4(String filePath) {
        try {
            File file = ResourceUtils.getFile(filePath);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            //解析display下的节点，第二级分类
            Element element = (Element) root.elements("display").get(0);
            List<Element> typeSetElements = element.elements();
            LinkedList<HashMap<String, Object>> list = new LinkedList<>();
            for (Element childFirst : typeSetElements) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("age", childFirst.attributeValue("age"));
                map.put("order", childFirst.attributeValue("order"));
                map.put("name", childFirst.attributeValue("name"));
                map.put("type-ref-pattern", childFirst.attributeValue("type-ref-pattern"));
                list.add(map);
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
