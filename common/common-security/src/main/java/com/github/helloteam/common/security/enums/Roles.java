package com.github.helloteam.common.security.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * 系统角色权限枚举类
 *
 * @author zdz
 * @date 2022/04/11 19:52
 */
public enum Roles implements GrantedAuthority {

    /**
     * 普通用户
     */
    ROLE_USER,

    /**
     * 超级管理员
     */
    ROLE_ADMIN,

    /**
     * 租户管理员
     */
    ROLE_TENANT_ADMIN,

    /**
     * 老师
     */
    ROLE_TEACHER,

    /**
     * 预览角色
     */
    ROLE_PREVIEW;

    /**
     * 获取当前角色所属的角色权限
     *
     * @return 当前角色所属的角色权限
     */
    @Override
    public String getAuthority() {
        return name();
    }

}
