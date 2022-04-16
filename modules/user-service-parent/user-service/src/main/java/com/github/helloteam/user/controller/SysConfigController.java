package com.github.helloteam.user.controller;

import com.github.helloteam.common.basic.dto.SysConfigDto;
import com.github.helloteam.common.basic.properties.SysProperties;
import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.core.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统配置controller
 *
 * @author zdz
 * @date 2022/04/16 14:04
 */
@AllArgsConstructor
@Api("系统配置信息管理")
@RestController
@RequestMapping("/v1/sysConfig")
public class SysConfigController extends BaseController {

    /**
     * 系统属性
     */
    private final SysProperties sysProperties;

    /**
     * 获取系统配置
     *
     * @return ResponseBean
     */
    @GetMapping
    @ApiOperation(value = "获取系统配置", notes = "获取系统配置")
    public ResponseBean<SysConfigDto> getSysConfig() {
        SysConfigDto sysConfigDto = new SysConfigDto();
        BeanUtils.copyProperties(sysProperties, sysConfigDto);
        return new ResponseBean<>(sysConfigDto);
    }

}