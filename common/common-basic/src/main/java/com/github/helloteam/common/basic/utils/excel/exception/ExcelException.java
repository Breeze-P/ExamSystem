package com.github.helloteam.common.basic.utils.excel.exception;

/**
 * Excel相关异常类
 *
 * @author zdz
 * @date 2022/04/16 11:23
 */
public class ExcelException extends RuntimeException {

    /**
     * Excel相关异常
     *
     * @param msg 异常信息
     */
    public ExcelException(String msg) {
        super(msg);
    }

}
