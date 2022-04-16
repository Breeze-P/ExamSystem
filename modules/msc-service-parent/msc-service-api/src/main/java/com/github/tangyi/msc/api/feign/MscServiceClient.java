package com.github.tangyi.msc.api.feign;

import com.github.tangyi.common.core.constant.ServiceConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.feign.config.CustomFeignConfig;
import com.github.tangyi.msc.api.dto.SmsDto;
import com.github.tangyi.msc.api.feign.factory.MscServiceClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 消息中心服务
 *
 * @author zdz
 * @date 2022/04/16 14:55
 */
@FeignClient(value = ServiceConstant.MSC_SERVICE,
        configuration = CustomFeignConfig.class,
        fallbackFactory = MscServiceClientFallbackFactory.class)
public interface MscServiceClient {

    /**
     * 发送短信
     *
     * @param smsDto smsDto
     * @return ResponseBean
     */
    @PostMapping("/v1/sms/sendSms")
    ResponseBean<?> sendSms(@RequestBody SmsDto smsDto);

}
