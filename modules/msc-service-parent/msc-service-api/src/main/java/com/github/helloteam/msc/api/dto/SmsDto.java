package com.github.helloteam.msc.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信数据传输对象类
 *
 * @author zdz
 * @date 2022/04/16 13:41
 */
@Data
public class SmsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 发送内容
     */
    private String content;

}
