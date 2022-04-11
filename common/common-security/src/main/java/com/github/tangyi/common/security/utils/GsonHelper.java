package com.github.tangyi.common.security.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 基于Gson的json工具类
 *
 * @author zdz
 * @date 2022/04/11 20:22
 */
public class GsonHelper {

    /**
     * Gson对象
     */
    private static final Gson gson = new Gson();

    /**
     * 单例 GsonHelper
     */
    private volatile static GsonHelper instance;

    /**
     * 获取单例
     */
    public static GsonHelper getInstance() {
        if (instance == null) {
            synchronized (GsonHelper.class) {
                if (instance == null)
                    instance = new GsonHelper();
            }
        }
        return instance;
    }


    /**
     * 将json转为对象
     *
     * @param json  json对象
     * @param clazz 转化后的类
     * @return T 指定的类
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 将json转为对象
     *
     * @param json json对象
     * @param type 转化后的Type
     * @return T 指定的类
     */
    public <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 将对象转为json
     *
     * @param src 所给对象
     * @return 转化后的Json字符串
     */
    public String toJson(Object src) {
        return gson.toJson(src);
    }

}
