
package com.hsg.plms.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static final String DAY_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public static Date stringToDate(String dateStr,String ...patterns){
        SimpleDateFormat dateFormat = null;
        Date result = null;
        if(patterns.length<1){
            dateFormat = new SimpleDateFormat(DAY_PATTERN);
            
        }else{
            dateFormat = new SimpleDateFormat(patterns[0]);
        }
        try {
            result = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            logger.error(" string parse date error", e);
        }
        return result;
    }
    
    public static Date stringToDateTime(String dateStr,String ...patterns){
        SimpleDateFormat dateFormat = null;
        Date result = null;
        if(patterns.length<1){
            dateFormat = new SimpleDateFormat(TIME_PATTERN);
            
        }else{
            dateFormat = new SimpleDateFormat(patterns[0]);
        }
        try {
            result = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            logger.error(" string parse date error", e);
        }
        return result;
    }
    
    public static String dateToDateString(Date date,String ...patterns){
        SimpleDateFormat dateFormat = null;
        String result = null;
        if(patterns.length<1){
            dateFormat = new SimpleDateFormat(DAY_PATTERN);
            
        }else{
            dateFormat = new SimpleDateFormat(patterns[0]);
        }       
        result = dateFormat.format(date);        
        return result;
    }    
    
    public static String dateToTimeString(Date date,String ...patterns){
        SimpleDateFormat dateFormat = null;
        String result = null;
        if(patterns.length<1){
            dateFormat = new SimpleDateFormat(TIME_PATTERN);
            
        }else{
            dateFormat = new SimpleDateFormat(patterns[0]);
        }       
        result = dateFormat.format(date);        
        return result;
    }    
}
