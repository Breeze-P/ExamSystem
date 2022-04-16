package com.github.helloteam.common.core.exceptions;

/**
 * 验证码过期异常类
 *
 * @author zdz
 * @date 2022/04/10 15:16
 */
public class ValidateCodeExpiredException extends CommonException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeExpiredException() {
    }

    public ValidateCodeExpiredException(String msg) {
        super(msg);
    }

}
