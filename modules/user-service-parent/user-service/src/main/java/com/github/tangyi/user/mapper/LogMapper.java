package com.github.tangyi.user.mapper;

import com.github.tangyi.common.basic.model.Log;
import com.github.tangyi.common.core.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志mapper
 *
 * @author zdz
 * @date 2022/04/16 11:58
 */
@Mapper
public interface LogMapper extends CrudMapper<Log> {

}
