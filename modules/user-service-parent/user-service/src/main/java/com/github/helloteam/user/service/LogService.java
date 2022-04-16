package com.github.helloteam.user.service;

import com.github.helloteam.common.basic.model.Log;
import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.user.mapper.LogMapper;
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
