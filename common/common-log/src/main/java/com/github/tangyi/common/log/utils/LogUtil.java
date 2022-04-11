package com.github.tangyi.common.log.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.github.tangyi.common.basic.model.Log;
import com.github.tangyi.common.core.constant.CommonConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 日志工具类
 *
 * @author zdz
 * @date 2022/04/11 15:47
 */
public class LogUtil {

    /**
     * 获取日志
     *
     * @return 日志
     */
    public static Log getLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.
                requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Log sysLog = new Log();
        sysLog.setCreator(Objects.requireNonNull(getUsername()));
        sysLog.setType(CommonConstant.STATUS_NORMAL);
        sysLog.setIp(ServletUtil.getClientIP(request));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setMethod(request.getMethod());
        sysLog.setUserAgent(request.getHeader("user-agent"));
        sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
        sysLog.setServiceId(getClientId());
        return sysLog;
    }

    /**
     * 获取客户端
     *
     * @return clientId
     */
    private static String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

}
