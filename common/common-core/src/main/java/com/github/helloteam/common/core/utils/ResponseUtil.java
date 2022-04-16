package com.github.helloteam.common.core.utils;

import com.github.helloteam.common.core.constant.ApiMsg;
import com.github.helloteam.common.core.model.ResponseBean;

/**
 * 封装常见的response操作的utils类
 *
 * @author zdz
 * @date 2022/04/10 15:24
 */
public class ResponseUtil {

	private ResponseUtil() {
	}

	/**
	 * 返回请求是否成功
	 *
	 * @param responseBean responseBean
	 * @return boolean
	 */
	public static boolean isSuccess(ResponseBean<?> responseBean) {
		return responseBean != null && responseBean.getCode() == ApiMsg.KEY_SUCCESS;
	}

}
