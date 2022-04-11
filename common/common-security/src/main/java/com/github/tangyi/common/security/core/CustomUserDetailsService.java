package com.github.tangyi.common.security.core;

import com.github.tangyi.common.security.mobile.MobileUser;
import com.github.tangyi.common.security.wx.WxUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 查询用户信息接口
 *
 * @author zdz
 * @date 2022/04/11 20:08
 */
public interface CustomUserDetailsService {

    /**
     * 根据用户名和租户标识查询
	 *
	 * @param tenantCode 租户标识
     * @param username   用户名
     * @return 用户信息
     */
    UserDetails loadUserByIdentifierAndTenantCode(String tenantCode, String username) throws UsernameNotFoundException;

    /**
     * 根据社交账号和租户标识查询
     *
     * @param tenantCode 租户标识
     * @param social     社交账号
	 * @param mobileUser 手机用户
     * @return 用户信息
     */
    UserDetails loadUserBySocialAndTenantCode(String tenantCode, String social, MobileUser mobileUser) throws UsernameNotFoundException;

    /**
     * 根据微信openId和租户标识查询
	 *
	 * @param tenantCode 租户标识
     * @param code       微信ID
     * @param wxUser     微信用户
     * @return UserDetails
     * @author tangyi
     * @date 2019/07/05 20:04:59
     */
    UserDetails loadUserByWxCodeAndTenantCode(String tenantCode, String code, WxUser wxUser) throws UsernameNotFoundException;

}
