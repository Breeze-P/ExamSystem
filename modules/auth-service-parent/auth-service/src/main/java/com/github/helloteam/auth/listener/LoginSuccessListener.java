package com.github.helloteam.auth.listener;

import com.github.helloteam.auth.model.CustomUserDetails;
import com.github.helloteam.common.basic.model.Log;
import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.constant.ServiceConstant;
import com.github.helloteam.common.core.utils.DateUtils;
import com.github.helloteam.common.security.event.CustomAuthenticationSuccessEvent;
import com.github.helloteam.common.security.utils.SysUtil;
import com.github.helloteam.user.api.dto.UserDto;
import com.github.helloteam.user.api.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 登录成功事件监听器
 *
 * @author zdz
 * @date 2022/04/14 22:36
 */
@Slf4j
@AllArgsConstructor
@Component
public class LoginSuccessListener implements ApplicationListener<CustomAuthenticationSuccessEvent> {

    /**
     * 用户service客户端
     */
    private final UserServiceClient userServiceClient;

    /**
     * 登录成功后的处理逻辑
     *
     * @param event 认证登录成功事件
     */
    @Override
    public void onApplicationEvent(CustomAuthenticationSuccessEvent event) {
        // 通过事件上下文获取当前登录成功用户的信息
        UserDetails userDetails = event.getUserDetails();
        if (userDetails instanceof CustomUserDetails) {
            // 通过用户信息获取相关需要的属性，如租户ID、用户名等
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            String tenantCode = customUserDetails.getTenantCode();
            String username = userDetails.getUsername();
            // 记录日志信息
            log.info("Login success, username: {} , tenantCode: {}", username, tenantCode);
            Log logInfo = new Log();
            logInfo.setTitle("用户登录");
            logInfo.setCommonValue(username, SysUtil.getSysCode(), tenantCode);
            logInfo.setTime(String.valueOf(System.currentTimeMillis() - customUserDetails.getStart()));
            logInfo.setType(CommonConstant.STATUS_NORMAL);
            // 获取发出此次请求的客户端的相关属性，如方法、请求URI、IP地址、用户浏览器信息等
            ServletRequestAttributes requestAttributes = currentRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                logInfo.setMethod(request.getMethod());
                logInfo.setRequestUri(request.getRequestURI());
                logInfo.setIp(request.getRemoteAddr());
                logInfo.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            }
            logInfo.setServiceId(ServiceConstant.AUTH_SERVICE);
            // 记录日志和登录时间
            UserDto userDto = new UserDto();
            userDto.setId(customUserDetails.getId());
            userDto.setIdentifier(username);
            userDto.setLoginTime(DateUtils.asDate(LocalDateTime.now()));
            saveLoginInfo(logInfo, userDto);
        }
    }

    /**
     * 异步记录用户登录日志
     *
     * @param logInfo 登录信息
     * @param userDto 用户数据传输对象
     */
    @Async
    public void saveLoginInfo(Log logInfo, UserDto userDto) {
        try {
            // 记录登录日志信息
            userServiceClient.saveLog(logInfo);
            // 更新用户的登录日志信息
            userServiceClient.updateLoginInfo(userDto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取当前request的相关属性
     *
     * @return 当前request的相关属性
     */
    private static ServletRequestAttributes currentRequestAttributes() {
        try {
            RequestAttributes requestAttr = RequestContextHolder.currentRequestAttributes();
            if (!(requestAttr instanceof ServletRequestAttributes)) {
                throw new IllegalStateException("current request is not a servlet request");
            }
            return (ServletRequestAttributes) requestAttr;
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }

}
