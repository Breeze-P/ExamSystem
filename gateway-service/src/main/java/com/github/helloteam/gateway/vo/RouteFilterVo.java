package com.github.helloteam.gateway.vo;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器信息
 *
 * @author zdz
 * @date 2022/04/11 21:44
 */
@Data
public class RouteFilterVo {

    /**
     * Filter名称
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
