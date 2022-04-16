package com.github.helloteam.common.core.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

/**
 * 封装常见的Json数据操作的utils类
 *
 * @author zdz
 * @date 2022/04/10 15:24
 */
@Slf4j
public class JsonMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    private static JsonMapper mapper;

    public static JsonMapper getInstance() {
        if (mapper == null) {
            mapper = new JsonMapper().enableSimple();
        }
        return mapper;
    }

    /**
     * 将所给对象转化为Json对象
     *
     * @param object 所给对象
     * @return 转化的Json对象
     */
    public String toJson(Object object) {
        try {
            return this.writeValueAsString(object);
        } catch (IOException e) {
            log.warn("Object to json failed: " + object, e);
            return null;
        }
    }

    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return this.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.warn("Json to object failed: " + jsonString, e);
            return null;
        }
    }

    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) this.readValue(jsonString, javaType);
        } catch (IOException e) {
            log.warn("Json to object failed: " + jsonString, e);
            return null;
        }
    }

    public static Object fromJsonObject(String jsonString, JavaType javaType) {
        return JsonMapper.getInstance().fromJson(jsonString, javaType);
    }


    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public JsonMapper enableSimple() {
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

    public ObjectMapper getMapper() {
        return this;
    }


    public static String toJsonString(Object object) {
        return JsonMapper.getInstance().toJson(object);
    }

    public static Object fromJsonString(String jsonString, Class<?> clazz) {
        return JsonMapper.getInstance().fromJson(jsonString, clazz);
    }

}
