package com.hqxh.fiamproperty.unit;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
        if(null==str){
            strArray= new String[]{""};
        }else {
            strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        }
        return strArray;
    }


}
