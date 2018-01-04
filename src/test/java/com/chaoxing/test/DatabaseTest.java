package com.chaoxing.test;


import com.chaoxing.test.model.City;
import com.chaoxing.test.service.impl.CityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 作用是读去json文件，然后把json文件中的数据存入数据库中。
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Autowired
    private CityServiceImpl cityService;


    @Test
    public void index(){
        String url = this.getClass().getClassLoader().getResource("area_data.json").toString();
        File file = new File(url.substring(6));
        System.out.println(url.substring(6));
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        System.out.println(buffer.toString());
        String string = buffer.toString().substring(1);
        ArrayList<City> cities = new ArrayList<>();
        String[] strings = string.split(",");
        for (String s : strings) {
            String[] split = s.split(":");
            City city = new City();
            System.out.println(split[0].substring(1,7));
            int length = split[1].length();
            System.out.println(split[1].substring(1,length-1));
            city.setCode(split[0].substring(1,7));
            city.setName(split[1].substring(1,length-1));
            cities.add(city);
            System.out.println("-------------");
        }
        System.out.println(cities);
        cityService.insertCityList(cities);
    }
}
