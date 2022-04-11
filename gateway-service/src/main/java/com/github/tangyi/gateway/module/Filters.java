package com.github.tangyi.gateway.module;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 网关过滤器
 *
 * @author zdz
 * @date 2022/04/11 21:42
 */
@Data
public class Filters extends BaseEntity<Filters> {

    /**
     * 路由ID
     */
    @NotBlank(message = "路由ID不能为空")
    private String routeId;

    /**
     * 过滤器名称
     */
    @NotBlank(message = "filter name不能为空")
    private String name;

    /**
     * 路由参数
     */
    private String args;

}
