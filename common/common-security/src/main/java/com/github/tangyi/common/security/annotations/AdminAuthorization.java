package com.github.tangyi.common.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * 超级管理员权限注解
 *
 * @author zdz
 * @date 2022/04/11 19:51
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(com.github.tangyi.common.security.enums.Roles).ROLE_ADMIN)")
public @interface AdminAuthorization {

}
