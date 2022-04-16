package com.github.helloteam.common.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * 超级管理员/租户管理员/教师/预览 角色权限注解
 *
 * @author zdz
 * @date 2022/04/11 19:54
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(com.github.helloteam.common.security.enums.Roles).ROLE_ADMIN) " +
        "or hasRole(T(com.github.helloteam.common.security.enums.Roles).ROLE_TENANT_ADMIN) " +
        "or hasRole(T(com.github.helloteam.common.security.enums.Roles).ROLE_TEACHER) " +
        "or hasRole(T(com.github.helloteam.common.security.enums.Roles).ROLE_PREVIEW)")
public @interface AdminTenantTeacherAuthorization {

}
