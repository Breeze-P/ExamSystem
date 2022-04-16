package com.github.helloteam.common.basic.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zdz
 * @date 2022/04/10 14:49
 */
@Data
public class SysConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上传地址
     */
    private String uploadUrl;

    /**
     * 默认头像
     */
    private String defaultAvatar;

    /**
     * 管理员账号
     */
    private String adminUser;

}
