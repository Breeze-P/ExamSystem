package com.github.tangyi.gateway.vo;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 断言信息
 *
 * @author zdz
 * @date 2022/04/11 21:44
 */
@Data
public class RoutePredicateVo {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
