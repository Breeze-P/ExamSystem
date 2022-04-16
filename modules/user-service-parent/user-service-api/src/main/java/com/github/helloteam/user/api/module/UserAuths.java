package com.github.helloteam.user.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 用户授权信息关联
 *
 * @author zdz
 * @date 2022/04/15 00:04
 */
@Data
public class UserAuths extends BaseEntity<UserAuths> {

    /**
     * 用户id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /**
     * 授权类型，1：用户名密码，2：手机号，3：邮箱，4：微信，5：QQ
     */
    private Integer identityType;

    /**
     * 唯一标识，如用户名、手机号
     */
    private String identifier;

    /**
     * 密码凭证，跟授权类型有关，如密码、第三方系统的token等
     */
    private String credential;

}