package com.github.helloteam.auth.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.github.helloteam.auth.api.module.WxSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信Service
 *
 * @author zdz
 * @date 2022/04/14 12:32
 */
@Slf4j
@AllArgsConstructor
@Service
public class WxSessionService {

    /**
     * 第三方微信service类
     */
    private final WxMaService wxMaService;

    /**
     * 根据code获取WxSession
     *
     * @param code code
     * @return WxSession
     */
    public WxSession getSession(String code) {
        WxSession session = null;
        try {
            WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
            session = new WxSession(result.getOpenid(), result.getSessionKey());
            log.info("Get wx session success，openId: {}, sessionKey: {}", session.getOpenId(), session.getSessionKey());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return session;
    }

    /**
     * 根据code获取WxSession
     *
     * @param code code
     * @return WxSession
     */
    public WxSession code2Session(String code) {
        WxSession session = null;
        try {
            WxMaJscode2SessionResult result = wxMaService.jsCode2SessionInfo(code);
            session = new WxSession(result.getOpenid(), result.getSessionKey());
            log.info("Get wx session success，openId: {}, sessionKey: {}", session.getOpenId(), session.getSessionKey());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return session;
    }

}
