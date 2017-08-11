package com.hqxh.fiamproperty.unit;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 17/7/26.
 * json操作类
 */

public class JsonUnit {

    //使用String的split 方法
    public static String[] convertStrToArray(String str) {
        String[] strArray = null;
        if (null == str) {
            strArray = new String[]{""};
        } else {
            strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        }
        return strArray;
    }


    /**
     * 转换日期格式
     **/
    public static String strToDateString(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String str=null;
        try {
            date = format.parse(strDate);
            str=format.format(date);
        } catch (ParseException e) {
            return null;
        }

        return str;
    }

}
