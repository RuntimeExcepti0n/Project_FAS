package com.paic.cost.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;

import java.util.Date;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 11:45
 */
public class DateUtil {

    public static  final String FORMAT_WITH_DAY = "yyyy-MM-dd";
    /**
     * 日期格式化字符串
     */
    public static  final String[] DATE_FORMATES = new String[]{"yyyy/MM/dd",FORMAT_WITH_DAY};

    public static Date parseDate(Object dateObj){
        if(dateObj == null){
            return null;
        }
        if(dateObj instanceof Date){
            return (Date)dateObj;
        }else if(dateObj instanceof String){
            if(StringUtils.isBlank((String)dateObj)){
                return null;
            }
            return DateUtils.parseDate((String)dateObj,DATE_FORMATES);
        }
        return null;
    }
}
