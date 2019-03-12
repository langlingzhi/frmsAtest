package com.nuanshui.frms.test.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    /**
     * 描述：获取当前时间
     *
     * @param dateFormat
     *            格式 "yyyy-MM-dd"
     * @return
     */
    public static String CurrentDate(String dateFormat){
        if(dateFormat==null||"".equals(dateFormat)){
            return null;
        }
        try{
            SimpleDateFormat dt=new SimpleDateFormat(dateFormat);
            Date date=new Date();
            Calendar cl=Calendar.getInstance();
            cl.setTime(date);
//            cl.set(Calendar.HOUR,Calendar.HOUR_OF_DAY);
            return dt.format(cl.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 描述：获取当前时间后一天
     *
     * @param time
     *            格式 "yyyy-MM-dd"
     * @return
     */
    public static String NextDate(String time){
        
        String nextdate = null;
        try{
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            date=sf.parse(time);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,10);
//            calendar.set(Calendar.HOUR,Calendar.HOUR_OF_DAY);
            nextdate=sf.format(calendar.getTime());

        }catch (Exception e){
            e.printStackTrace();
        }
        return nextdate;
    }
    /*
    *描述：将日期格式转换为date
     */
    public static Date uvdate(String time){
        Date date=null;
        try{
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date=sf.parse(time);

        }catch (Exception e){
            e.printStackTrace();
        }
        return date;

    }
    /*
    *描述：判断当前年份是否是闰年
     */
    public static Boolean leapyear(int year){
        Boolean ly=null;
        try{
            GregorianCalendar gc=new GregorianCalendar();
            if(gc.isLeapYear(year)){
                 ly=true;
            }
            else
            {
                ly=false;
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return ly;
    }
    /*
   *获取时间差秒级别
    */
    public static String TimeDifference(Date begintime){
        String Timedif=new String();
        try{
            Date date=new Date();
            Timedif = String.valueOf((date.getTime() - begintime.getTime()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Timedif;
    }
}
