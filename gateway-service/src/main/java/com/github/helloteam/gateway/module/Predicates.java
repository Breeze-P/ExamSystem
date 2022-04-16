package com.github.helloteam.gateway.module;

import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 断言
 *
 * @author zdz
 * @date 2022/04/11 21:43
 */
@Data
public class Predicates extends BaseEntity<Predicates> {

    /**
     * 路由ID
     */
    @NotBlank(message = "路由ID不能为空")
    private String routeId;

    /**
     * 断言名称
     */
    @NotBlank(message = "predicates name不能为空")
    private String name;

    /**
     * 断言参数
     */
    private String args;

}
