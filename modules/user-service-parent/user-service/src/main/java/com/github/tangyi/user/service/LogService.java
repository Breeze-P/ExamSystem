package com.github.tangyi.user.service;

import com.github.tangyi.common.basic.model.Log;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.user.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
 * 日志service
 *
 * @author zdz
 * @date 2022/04/16 12:12
 */
@Service
public class LogService extends CrudService<LogMapper, Log> {

}
