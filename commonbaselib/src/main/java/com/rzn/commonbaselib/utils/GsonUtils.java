package com.rzn.commonbaselib.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zz on 2018/4/13.
 */

public class GsonUtils {

    /**
     * @param gson
     * @param parseobj
     * @param createClass
     * @return
     * gson解析对象
     */
    public  static  <T> T gsonParseBean(Gson gson ,Object parseobj ,Class createClass){
       return (T) gson.fromJson(gson.toJson(parseobj),createClass);
    }


    /**
     * @param gson
     * @param obj
     * @param <T>
     * @return
     * gson解析集合
     * 根据泛型返回解析制定的类型
     */
    public static <T> T gsonParseList(Gson gson,Object obj,Class<T> Class){

         Type type = new TypeToken<List<Class>>(){}.getType();
          return gson.fromJson(gson.toJson(obj), type);
    }

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }



}
