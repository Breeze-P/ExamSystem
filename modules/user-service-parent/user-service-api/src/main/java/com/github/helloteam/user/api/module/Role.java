package com.github.helloteam.user.api.module;

import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 角色信息
 *
 * @author zdz
 * @date 2022/04/15 00:07
 */
@Data
public class Role extends BaseEntity<Role> {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色标识
     */
    @NotBlank(message = "角色标识不能为空")
    private String roleCode;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 菜单ID
     */
    private String menuIds;

    /**
     * 是否默认 0-否，1-是
     */
    private Integer isDefault;

}
