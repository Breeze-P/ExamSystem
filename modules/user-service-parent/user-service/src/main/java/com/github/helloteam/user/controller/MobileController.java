package com.github.helloteam.user.controller;


import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.core.web.BaseController;
import com.github.helloteam.user.service.MobileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手机管理Controller
 *
 * @author zdz
 * @date 2022/04/16 14:01
 */
@Slf4j
@AllArgsConstructor
@Api("手机管理")
@RestController
@RequestMapping("/v1/mobile")
public class MobileController extends BaseController {

    /**
     * 手机号service
     */
    private final MobileService mobileService;

    /**
     * 发送短信
     *
     * @param mobile     mobile
     * @return ResponseBean
     */
    @GetMapping("sendSms/{mobile}")
    @ApiOperation(value = "发送短信", notes = "发送短信到指定的手机号")
    @ApiImplicitParam(name = "mobile", value = "mobile", required = true, dataType = "String", paramType = "path")
    public ResponseBean<Boolean> sendSms(@PathVariable String mobile) {
        return mobileService.sendSms(mobile);
    }

}
