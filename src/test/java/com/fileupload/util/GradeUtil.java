package com.fileupload.util;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className:
 * @Description:
 * @auther:ynhj
 * @date:18:42 2018-08-14
 * @version: ver 1.0
 */
public class GradeUtil {
    public static class RealGrade{
        private int id;
        private String name;
        private String value;
        private Map<Integer,String> nameMap = new HashMap<Integer, String>(){{
            put(1,"一年级");
            put(2,"二年级");
            put(3,"三年级");
            put(4,"四年级");
            put(5,"五年级");
            put(6,"六年级");
            put(7,"七年级");
            put(8,"八年级");
            put(9,"九年级");
            put(10,"十年级");
            put(11,"十一年级");
            put(12,"十二年级");
        }};
        private Map<Integer,String> valueMap = new HashMap<Integer, String>(){{
            put(1,"one");
            put(2,"two");
            put(3,"three");
            put(4,"four");
            put(5,"five");
            put(6,"six");
            put(7,"seven");
            put(8,"eight");
            put(9,"nine");
            put(10,"ten");
            put(11,"eleven");
            put(12,"twelve");
        }};
        public RealGrade(Integer id){
            this.id = id;
            this.name = nameMap.get(id);
            this.value =valueMap.get(id);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
    private static class DayCompare{
        private int year;
        private int month;
        private int day;

        private DayCompare() {
        }

        private DayCompare(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public static DayCompare builder() {
            return new DayCompare() ;
        }


    }
    public static String getYear(String str){
        String year = null;
        String pattern = "\\d{4}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find( )) {
            year = m.group();
        }
        return year;
    }
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    public static RealGrade getGrade(String gradeName)  throws Exception{
        String substring = getYear(gradeName);
        substring+="-09";
        final Date orginDate = sdf.parse(substring);
        Calendar cal = Calendar.getInstance();


        return new RealGrade(yearCompare(orginDate,new Date()));
    }
    public static DayCompare dayComparePrecise(Date fromDate,Date toDate){
        Calendar from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);

        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        return new DayCompare(year,month,day);
    }
    public static Integer yearCompare(Date fromDate,Date toDate){
        DayCompare result = dayComparePrecise(fromDate, toDate);
        double month = result.getMonth();
        double year = result.getYear();
        //返回2位小数，并且四舍五入
        DecimalFormat df = new DecimalFormat("######0.0");
        return Double.valueOf(df.format(year + month / 12)).intValue();
    }

    public static void main(String[] args) {
        try {
            System.out.println(getGrade("莆田2011级").getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
