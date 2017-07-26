package com.hqxh.fiamproperty.unit;

/**
 * Created by apple on 17/7/26.
 * json操作类
 */

public class JsonUnit {

    //使用String的split 方法
    public static String[] convertStrToArray(String str){
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }
}
