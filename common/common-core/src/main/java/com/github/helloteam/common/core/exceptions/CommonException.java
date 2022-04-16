package com.github.helloteam.common.core.exceptions;

/**
 * 常见公共异常类
 *
 * @author zdz
 * @date 2022/04/10 15:16
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CommonException() {}

    public CommonException(String msg) {
        super(msg);
    }

    public CommonException(Throwable throwable) {
        super(throwable);
    }

    public CommonException(Throwable throwable, String msg) {
        super(throwable);
    }
}
