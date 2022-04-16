package com.github.tangyi.msc.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 封装短信服务返回的结果
 *
 * @author zdz
 * @date 2022/04/16 14:55
 */
@Data
public class SmsResponse {

    @JsonProperty("Message")
    private String message;

    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("Code")
    private String code;

}
