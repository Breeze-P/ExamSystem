package com.github.tangyi.common.security.core;

import org.springframework.security.core.GrantedAuthority;

/**
 * GrantedAuthority封装
 *
 * @author zdz
 * @date 2022/04/11 20:08
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = -5588742812922519390L;

    /**
     * 角色权限名
     */
    private String roleName;

    /**
     * 构造器
     * @param roleName 角色权限信息
     */
    public GrantedAuthorityImpl(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色权限信息
     *
     * @return 角色权限信息
     */
    @Override
    public String getAuthority() {
        return roleName;
    }

}
