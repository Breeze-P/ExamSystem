package com.github.tangyi.auth.security;

import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.exceptions.TenantNotFoundException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseUtil;
import com.github.tangyi.user.api.feign.UserServiceClient;
import com.github.tangyi.user.api.module.Tenant;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 校验租户
 *
 * @author zdz
 * @date 2022/04/14 22:57
 */
@AllArgsConstructor
@Aspect
@Component
public class ValidateTenantAspect {

    /**
     * 用户service客户端
     */
    private final UserServiceClient userServiceClient;

    /**
     * 校验逻辑
     *
     * @param tenantCode 租户ID
     * @throws TenantNotFoundException “未找到对应租户”异常
     */
    @Before("execution(* com.github.tangyi.auth.security.CustomUserDetailsServiceImpl.load*(..)) && args(tenantCode,..)")
    public void validateTenantCode(String tenantCode) throws TenantNotFoundException {
        // 获取租户ID
        if (StringUtils.isBlank(tenantCode)) {
            throw new TenantNotFoundException("tenantCode cant not be null");
        }
        // 根据租户ID获取租户实体Bean
        ResponseBean<Tenant> tenantResponseBean = userServiceClient.findTenantByTenantCode(tenantCode);
        if (!ResponseUtil.isSuccess(tenantResponseBean)) {
            throw new ServiceException("get tenant info failed: " + tenantResponseBean.getMsg());
        }
        // 根据租户实体Bean获取租户实体
        Tenant tenant = tenantResponseBean.getData();
        if (tenant == null) {
            throw new TenantNotFoundException("tenant does not exist");
        }
    }

}
