package me.loveshare.util.common;

import com.alibaba.fastjson.JSON;

/**
 * Created by Tony on 2017/6/29.
 * Json操作类
 */
public final class JsonUtils {

    /**
     * 字符串转Java bean对象
     */
    public static final <T> T string2object(String json, Class<T> tC) {
        return JSON.parseObject(json, tC);
    }

    /**
     * Java bean对象转换字符串
     */
    public static final <T> String object2string(T t) {
        return JSON.toJSONString(t);
    }
}
