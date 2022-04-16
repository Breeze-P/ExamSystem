package com.github.helloteam.common.log.annotation;

import java.lang.annotation.*;

/**
 * 日志注解类
 *
 * @author zdz
 * @date 2022/04/11 15:38
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 日志描述信息
     *
     * @return {String}
     */
    String value();

}
