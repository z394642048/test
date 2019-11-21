package com.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Word2HtmlTest {



    public static String readFile(String filePathAndName) {
        String fileContent = "";
        try {
            File f = new File(filePathAndName);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    //将读取到的字符拼接
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        System.out.println("fileContent:" + fileContent);
        return fileContent;
    }

}
