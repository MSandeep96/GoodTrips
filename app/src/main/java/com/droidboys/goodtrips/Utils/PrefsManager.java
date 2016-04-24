package com.droidboys.goodtrips.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sandeep on 24-Apr-16.
 */
public class PrefsManager {
    public static String auth=null;
    public static void setLoginAuth(String auth_key,Context context){
        SharedPreferences.Editor manager=context.getSharedPreferences(ConstantsProj.PROJ_PREFS,Context.MODE_PRIVATE).edit();
        manager.putString(ConstantsProj.AUTH_CODE,null);
        auth=auth_key;
        manager.apply();
    }

    public static String  getLoginAuth(Context context){
        SharedPreferences manager=context.getSharedPreferences(ConstantsProj.PROJ_PREFS,Context.MODE_PRIVATE);
        if(auth!=null){
            return auth;
        }else{
            return manager.getString(ConstantsProj.AUTH_CODE,"");
        }
    }

    public static void setLoggedIn(Boolean isLog,Context context){
        SharedPreferences.Editor manager=context.getSharedPreferences(ConstantsProj.PROJ_PREFS,Context.MODE_PRIVATE).edit();
        manager.putBoolean(ConstantsProj.AUTH_CODE,false);
        manager.apply();
    }

    public static boolean getLoggedIn(Context context){
        SharedPreferences manager=context.getSharedPreferences(ConstantsProj.PROJ_PREFS,Context.MODE_PRIVATE);
        return manager.getBoolean(ConstantsProj.IS_LOGGED_IN,false);
    }

}
