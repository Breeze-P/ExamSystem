package com.github.helloteam.auth.security;

import com.github.helloteam.auth.api.module.WxSession;
import com.github.helloteam.auth.model.CustomUserDetails;
import com.github.helloteam.auth.service.WxSessionService;
import com.github.helloteam.common.basic.enums.LoginTypeEnum;
import com.github.helloteam.common.basic.vo.UserVo;
import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.exceptions.CommonException;
import com.github.helloteam.common.core.exceptions.ServiceException;
import com.github.helloteam.common.core.exceptions.TenantNotFoundException;
import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.core.utils.DateUtils;
import com.github.helloteam.common.core.utils.ResponseUtil;
import com.github.helloteam.common.security.core.CustomUserDetailsService;
import com.github.helloteam.common.security.core.GrantedAuthorityImpl;
import com.github.helloteam.common.security.mobile.MobileUser;
import com.github.helloteam.common.security.wx.WxUser;
import com.github.helloteam.user.api.dto.UserDto;
import com.github.helloteam.user.api.enums.IdentityType;
import com.github.helloteam.user.api.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 从数据库获取用户信息
 *
 * @author zdz
 * @date 2022/04/14 23:24
 */
@AllArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    /**
     * “获取用户信息失败”提示
     */
    private static final String GET_USER_INFO_FAIL = "get user information failed: ";

    /**
     * 用户service客户端
     */
    private final UserServiceClient userServiceClient;

    /**
     * 微信service
     */
    private final WxSessionService wxService;

    /**
     * 根据用户名和租户标识查询指定用户的信息
     *
     * @param tenantCode 租户标识
     * @param username   用户名
     * @return 所给租户和用户名对应的用户信息
     * @throws UsernameNotFoundException “未找到用户名”异常
     * @throws TenantNotFoundException   “未找到租户”异常
     */
    @Override
    public UserDetails loadUserByIdentifierAndTenantCode(String tenantCode, String username)
            throws UsernameNotFoundException, TenantNotFoundException {
        long start = System.currentTimeMillis();
        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(username, tenantCode);
        if (!ResponseUtil.isSuccess(userVoResponseBean)) {
            throw new ServiceException(GET_USER_INFO_FAIL + userVoResponseBean.getMsg());
        }
        UserVo userVo = userVoResponseBean.getData();
        // 若未找到所给用户名对应的用户
        if (userVo == null) {
            throw new UsernameNotFoundException("user does not exist");
        }
        return new CustomUserDetails(username, userVo.getCredential(),
                CommonConstant.STATUS_NORMAL.equals(userVo.getStatus()),
                getAuthority(userVo), userVo.getTenantCode(), userVo.getId(), start, LoginTypeEnum.PWD);
    }

    /**
     * 根据社交账号和租户标识查询指定用户的信息
     *
     * @param tenantCode 租户标识
     * @param social     社交账号
     * @param mobileUser 手机号码
     * @return 所给租户和社交账号对应的用户信息
     */
    @Override
    public UserDetails loadUserBySocialAndTenantCode(String tenantCode, String social, MobileUser mobileUser)
            throws UsernameNotFoundException {
        long start = System.currentTimeMillis();
        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(social, IdentityType.PHONE_NUMBER.getValue(), tenantCode);
        if (!ResponseUtil.isSuccess(userVoResponseBean)) {
            throw new ServiceException(GET_USER_INFO_FAIL + userVoResponseBean.getMsg());
        }
        UserVo userVo = userVoResponseBean.getData();
        // 第一次登录
        if (userVo == null) {
            UserDto userDto = new UserDto();
            // 用户的基本信息
            if (mobileUser != null) {
                BeanUtils.copyProperties(mobileUser, userDto);
            }
            userDto.setIdentifier(social);
            userDto.setCredential(social);
            userDto.setIdentityType(IdentityType.PHONE_NUMBER.getValue());
            userDto.setLoginTime(DateUtils.asDate(LocalDateTime.now()));
            // 注册账号
            ResponseBean<Boolean> response = userServiceClient.registerUser(userDto);
            if (!ResponseUtil.isSuccess(response)) {
                throw new ServiceException("register failed: " + response.getMsg());
            }
            // 重新获取用户信息
            userVoResponseBean = userServiceClient.findUserByIdentifier(social, IdentityType.PHONE_NUMBER.getValue(), tenantCode);
            if (!ResponseUtil.isSuccess(userVoResponseBean)) {
                throw new ServiceException(GET_USER_INFO_FAIL + userVoResponseBean.getMsg());
            }
            userVo = userVoResponseBean.getData();
        }
        return new CustomUserDetails(userVo.getIdentifier(), userVo.getCredential(),
                CommonConstant.STATUS_NORMAL.equals(userVo.getStatus()),
                getAuthority(userVo), userVo.getTenantCode(), userVo.getId(), start, LoginTypeEnum.SMS);
    }

    /**
     * 根据微信code和租户标识查询
     * 将code换成openId和sessionKey
     *
     * @param tenantCode tenantCode
     * @param code       code
     * @param wxUser     wxUser
     * @return 所给租户和微信账号对应的用户信息
     */
    @Override
    public UserDetails loadUserByWxCodeAndTenantCode(String tenantCode, String code, WxUser wxUser)
            throws UsernameNotFoundException {
        long start = System.currentTimeMillis();
        // 根据code获取openId和sessionKey
        WxSession wxSession = wxService.code2Session(code);
        // 若未找到对应的微信账号
        if (wxSession == null) {
            throw new CommonException("get openId failed");
        }
        // 获取用户信息
        ResponseBean<UserVo> userVoResponseBean = userServiceClient.findUserByIdentifier(wxSession.getOpenId(), IdentityType.WE_CHAT.getValue(), tenantCode);
        if (!ResponseUtil.isSuccess(userVoResponseBean)) {
            throw new ServiceException(GET_USER_INFO_FAIL + userVoResponseBean.getMsg());
        }
        UserVo userVo = userVoResponseBean.getData();
        // 为空说明是第一次登录，需要将用户信息增加到数据库里
        if (userVo == null) {
            UserDto userDto = new UserDto();
            // 用户的基本信息
            if (wxUser != null) {
                BeanUtils.copyProperties(wxUser, userDto);
            }
            userDto.setIdentifier(wxSession.getOpenId());
            userDto.setCredential(wxSession.getOpenId());
            userDto.setIdentityType(IdentityType.WE_CHAT.getValue());
            userDto.setLoginTime(DateUtils.asDate(LocalDateTime.now()));
            // 注册账号
            ResponseBean<Boolean> response = userServiceClient.registerUser(userDto);
            if (!ResponseUtil.isSuccess(response)) {
                throw new ServiceException("register failed: " + response.getMsg());
            }
            // 重新获取用户信息
            userVoResponseBean = userServiceClient.findUserByIdentifier(wxSession.getOpenId(), IdentityType.WE_CHAT.getValue(), tenantCode);
            if (!ResponseUtil.isSuccess(userVoResponseBean)) {
                throw new ServiceException(GET_USER_INFO_FAIL + userVoResponseBean.getMsg());
            }
            userVo = userVoResponseBean.getData();
        }
        return new CustomUserDetails(userVo.getIdentifier(), userVo.getCredential(),
                CommonConstant.STATUS_NORMAL.equals(userVo.getStatus()),
                getAuthority(userVo), userVo.getTenantCode(), userVo.getId(), start, LoginTypeEnum.WECHAT);
    }

    /**
     * 获取用户权限
     *
     * @param userVo 用户VO
     * @return 用户权限set
     */
    private Set<GrantedAuthority> getAuthority(UserVo userVo) {
        return userVo.getRoleList()
                .stream()
                .map(role -> new GrantedAuthorityImpl(role.getRoleCode().toUpperCase()))
                .collect(Collectors.toSet());
    }

}
