package com.github.helloteam.user.controller;

import com.github.helloteam.common.core.web.BaseController;
import com.github.helloteam.user.service.UserService;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * 验证码controller
 *
 * @author zdz
 * @date 2022/04/16 14:08
 */
@AllArgsConstructor
@Api("生成验证码")
@RestController
@RequestMapping(value = "/v1/code")
public class ValidateCodeController extends BaseController {

    /**
     * 验证码生成器
     */
    private final Producer producer;

    /**
     * 用户service
     */
    private final UserService userService;

    /**
     * 生成验证码逻辑
     *
     * @param random random
     * @param response 响应
     */
    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "random", value = "随机数", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/{random}")
    public void produceCode(@PathVariable String random, HttpServletResponse response) throws Exception {
        // 设置响应的消息头等信息
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        userService.saveImageCode(random, text);
        try (ServletOutputStream out = response.getOutputStream()) {
			ImageIO.write(image, "JPEG", out);
		}
    }

}
