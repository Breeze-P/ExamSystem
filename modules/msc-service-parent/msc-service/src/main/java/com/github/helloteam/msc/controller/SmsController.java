package com.github.helloteam.msc.controller;


import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.core.web.BaseController;
import com.github.helloteam.msc.api.dto.SmsDto;
import com.github.helloteam.msc.api.model.SmsResponse;
import com.github.helloteam.msc.service.SmsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送短信接口
 *
 * @author zdz
 * @date 2022/04/16 14:56
 */
@Slf4j
@AllArgsConstructor
@Api("发送短信")
@RestController
@RequestMapping(value = "/v1/sms")
public class SmsController extends BaseController {

    /**
     * 短信service
     */
    private final SmsService smsService;

    /**
     * 发送短信
     *
     * @param smsDto smsDto
     * @return ResponseBean
     */
    @PostMapping("sendSms")
    public ResponseBean<SmsResponse> sendSms(@RequestBody SmsDto smsDto) {
        log.info("Send message to {}, content: {}", smsDto.getReceiver(), smsDto.getContent());
        SmsResponse smsResponse = smsService.sendSms(smsDto);
        log.info("Send message success, response: {}", smsResponse);
        return new ResponseBean<>(smsResponse);
    }

}
