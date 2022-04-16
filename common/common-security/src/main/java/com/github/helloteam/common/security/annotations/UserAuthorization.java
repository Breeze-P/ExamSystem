package com.github.helloteam.common.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * 普通用户角色权限注解
 *
 * @author zdz
 * @date 2022/04/11 19:54
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(com.github.helloteam.common.security.enums.Roles).ROLE_USER)")
public @interface UserAuthorization {

}
