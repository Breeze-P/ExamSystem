package com.github.tangyi.common.core.exceptions;

/**
 * token非法异常类
 *
 * @author zdz
 * @date 2022/04/10 15:16
 */
public class InvalidAccessTokenException extends CommonException {

    private static final long serialVersionUID = -7285211528095468156L;

    public InvalidAccessTokenException() {
    }

    public InvalidAccessTokenException(String msg) {
        super(msg);
    }

}
