package com.github.tangyi.common.basic.vo;

import com.github.tangyi.common.basic.model.Log;
import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * log VO类
 *
 * @author zdz
 * @date 2022/04/10 14:55
 */
@Data
public class LogVo extends BaseEntity<LogVo> {

    /**
     * 日志实例
     */
    private Log log;

    /**
     * 用户名
     */
    private String username;

}
