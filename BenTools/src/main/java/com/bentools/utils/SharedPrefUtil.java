package com.bentools.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @author zhangmeiyan
 *
 */
public class SharedPrefUtil {
    private static final String NAME = "day_landscape_xml";

    public static SharedPrefUtil instance = null;

    private Context context;

    private SharedPrefUtil(Context context) {
        this.context = context;
    }

    public static SharedPrefUtil getInstance(Context context) {
        if (null == instance) {
            instance = new SharedPrefUtil(context);
        }
        return instance;
    }

    private SharedPreferences sp;

    public SharedPreferences getSp() {
        if (sp == null)
            sp = context.getSharedPreferences(getSpFileName(),
                    Context.MODE_PRIVATE);
        return sp;
    }

    public SharedPreferences.Editor getEdit() {
        return getSp().edit();
    }

    private String getSpFileName() {
        return NAME;
    }

    
    public void setPassword(String sessionKey) {
        getEdit().putString("password", sessionKey).commit();
    }

    public String getPassword() {
        return getSp().getString("password", "");
    }


    /**
     * 是否登录
     */
    public void setLogined(boolean bool) {
        getEdit().putBoolean("is_logined", bool).commit();
    }

    public boolean getIsLogined() {
        return getSp().getBoolean("is_logined", false);
    }


    /**
     * 登录账号
     */
    public void setLoginAccount(String loginAccount) {
        getEdit().putString("login_account", loginAccount).commit();
    }

    public String getLoginAccount() {
        return getSp().getString("login_account", "");
    }


    public void clear() {

    }

    public void clearUserLogout(){
        getEdit().putBoolean("is_logined", false).commit();
    }
}
