package com.github.helloteam.common.security.wx;

import lombok.Data;

import java.io.Serializable;

/**
 * 表示微信用户的POJO类
 *
 * @author zdz
 * @date 2022/04/11 20:09
 */
@Data
public class WxUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 详细描述
     */
    private String userDesc;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 语言
     */
    private String languange;

}
