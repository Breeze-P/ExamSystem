package com.github.tangyi.user.api.constant;

/**
 * 封装租户相关的常量
 *
 * @author zdz
 * @date 2022/04/15 00:01
 */
public class TenantConstant {

    /**
     * 待审核
     */
    public static final Integer PENDING_AUDIT = 0;

    /**
     * 审核通过
     */
    public static final Integer APPROVAL = 1;

    /**
     * 审核不通过
     */
    public static final Integer AUDIT_FAIL = 2;

}
