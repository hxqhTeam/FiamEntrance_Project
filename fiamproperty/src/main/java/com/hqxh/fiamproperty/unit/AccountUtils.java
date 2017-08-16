package com.hqxh.fiamproperty.unit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.model.R_PERSONS.PERSION;


/**
 * 登录帐号管理Created by yw on 2015/5/5.
 */
public class AccountUtils {


    /**
     * 记录登录返回信息
     * @param cxt
     * @param persion
     *
     */
    public static void setLoginDetails(Context cxt, PERSION persion){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.login_personId), persion.getPERSONID())
                .putString(cxt.getString(R.string.login_displayName), persion.getDISPLAYNAME()).commit();
    }



    public static String getpersonId(Context cxt){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.login_personId), "");
    }


}
