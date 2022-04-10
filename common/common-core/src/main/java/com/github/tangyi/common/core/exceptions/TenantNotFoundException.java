package com.github.tangyi.common.core.exceptions;

/**
 * 租户不存在异常类
 *
 * @author zdz
 * @date 2022/04/10 15:16
 */
public class TenantNotFoundException extends CommonException {

    private static final long serialVersionUID = -7285211528095468156L;

    public TenantNotFoundException() {
    }

    public TenantNotFoundException(String msg) {
        super(msg);
    }

}
