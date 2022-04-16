package com.github.helloteam.user.service;

import cn.hutool.core.util.RandomUtil;
import com.github.helloteam.common.basic.enums.LoginTypeEnum;
import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.exceptions.ServiceException;
import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.core.utils.ResponseUtil;
import com.github.helloteam.common.security.constant.SecurityConstant;
import com.github.helloteam.msc.api.constant.SmsConstant;
import com.github.helloteam.msc.api.dto.SmsDto;
import com.github.helloteam.msc.api.feign.MscServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 手机管理service
 *
 * @author zdz
 * @date 2022/04/16 13:41
 */
@Slf4j
@AllArgsConstructor
@Service
public class MobileService {

    /**
     * Redis template
     */
    private final RedisTemplate redisTemplate;

    /**
     * Msc service客户端
     */
    private final MscServiceClient mscServiceClient;

    /**
     * 发送短信
     *
     * @param mobile 手机号码
     * @return 包含操作处理结果的响应Bean
     */
    public ResponseBean<Boolean> sendSms(String mobile) {
        String key = CommonConstant.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + "@" + mobile;
        String code = RandomUtil.randomNumbers(Integer.parseInt(CommonConstant.CODE_SIZE));
        log.debug("Generate validate code success: {}, {}", mobile, code);
        redisTemplate.opsForValue().set(key, code, SecurityConstant.DEFAULT_SMS_EXPIRE, TimeUnit.SECONDS);
        // 调用消息中心服务，发送短信验证码
        SmsDto smsDto = new SmsDto();
        smsDto.setReceiver(mobile);
        smsDto.setContent(String.format(SmsConstant.SMS_TEMPLATE, code));
        // 发送短信验证码
        ResponseBean<?> result = mscServiceClient.sendSms(smsDto);
        if (!ResponseUtil.isSuccess(result)) {
            throw new ServiceException("Send validate code error: " + result.getMsg());
        }
        log.info("Send validate result: {}", result.getData());
        return new ResponseBean<>(Boolean.TRUE, code);
    }

}
