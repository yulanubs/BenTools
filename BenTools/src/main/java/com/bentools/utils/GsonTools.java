package com.bentools.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
	 * @ClassName: GsonTools<BR>
     * @Describe：Gson工具类<BR>
     * @Author: Jekshow
	 * @Extends：<BR>
     * @Version:1.0 
     * @date:2016-6-15 上午9:12:34
 */
public class GsonTools {  
	  
    public static <T> T getPerson(String jsonString, Class<T> cls) {  
        T t = null;  
        try {  
            Gson gson = new Gson();  
            t = gson.fromJson(jsonString, cls);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return t;  
    }  
  
    public static <T> List<T> getPersons(String jsonString, Class<T> cls) {  
        List<T> list = new ArrayList<T>();  
        try {  
            Gson gson = new Gson();  
            list = gson.fromJson(jsonString, new TypeToken<List<T>>() {  
            }.getType());  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return list;  
    }  
  
    public static List<String> getList(String jsonString) {  
        List<String> list = new ArrayList<String>();  
        try {  
            Gson gson = new Gson();  
            list = gson.fromJson(jsonString, new TypeToken<List<String>>() {  
            }.getType());  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
        return list;  
  
    }  
    
    public static List<Map<String,Object>> listKeyMap(String jsonString){  
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();  
        try {  
            Gson gson = new Gson();  
            list = gson.fromJson(jsonString, new TypeToken<List<Map<String,Object>>>() {  
            }.getType());  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
        return list;  
    }  
      
      
}  
