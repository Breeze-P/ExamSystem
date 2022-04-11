package com.github.tangyi.oss.exceptions;

import com.github.tangyi.common.core.exceptions.CommonException;

/**
 * oss exception (Object Storage Service 对象存储服务)
 *
 * @author zdz
 * @date 2022/04/11 16:16
 */
public class OssException extends CommonException {

	public OssException(String msg) {
		super(msg);
	}

	public OssException(Throwable throwable, String msg) {
		super(throwable, msg);
	}

}
