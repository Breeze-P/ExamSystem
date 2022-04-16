package com.github.helloteam.auth.api.module;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装微信登录状态
 *
 * @author zdz
 * @date 2022/04/14 23:40
 */
@Data
@AllArgsConstructor
public class WxSession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * open ID
     */
    private String openId;

    /**
     * Session key
     */
    private String sessionKey;

}
