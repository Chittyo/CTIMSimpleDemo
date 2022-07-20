package com.ctim.simpledemo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用谷歌 Gson 实现 Json 串和 Java Bean 互转
 */
public class JsonHelper {
    public static String toJson(Object src){
        return new Gson().toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        return new Gson().fromJson(json, clazz);
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) throws Exception {
        List<T> lst =  new ArrayList<T>();

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }
}
