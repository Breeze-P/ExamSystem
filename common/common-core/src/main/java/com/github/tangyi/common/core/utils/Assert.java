package com.github.tangyi.common.core.utils;

/**
 * 封装常见的断言操作的utils类
 *
 * @author zdz
 * @date 2022/04/10 15:24
 */
public class Assert {

    /**
     * 非空校验
     *
     * @param object  object
     * @param message message
     * @author zdz
     * @date 2022/04/10 15:24
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
