package com.github.helloteam.common.security.tenant;

/**
 * 通过本地线程ThreadLocal保存租户code
 *
 * @author zdz
 * @date 2022/04/11 20:14
 */
public class TenantContextHolder {

    /**
     * 本地上下文线程
     */
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    /**
     * 设置上下文中的租户code
     * @param tenantCode 租户code
     */
    public static void setTenantCode(String tenantCode) {
        CONTEXT.set(tenantCode);
    }

    /**
     * 获取当前上下文中的租户code
     * @return 当前上下文中的租户code
     */
    public static String getTenantCode() {
        return CONTEXT.get();
    }

    /**
     * 清理上下文信息
     */
    public static void clear() {
        CONTEXT.remove();
    }

}
