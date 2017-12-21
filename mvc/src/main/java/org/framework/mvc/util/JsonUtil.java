package org.framework.mvc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    public static String toJson(Object bean){
        return new Gson().toJson(bean);
    }
    public static String toJson(Object bean,String format){
        return new GsonBuilder().setDateFormat(format).create().toJson(bean);
    }

}
