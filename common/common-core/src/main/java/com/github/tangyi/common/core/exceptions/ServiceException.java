package com.github.tangyi.common.core.exceptions;

/**
 * 服务异常类
 *
 * @author zdz
 * @date 2022/04/10 15:16
 */
public class ServiceException extends CommonException {

	private static final long serialVersionUID = -7285211528095468156L;

	public ServiceException() {
	}

	public ServiceException(String msg) {
		super(msg);
	}

}
