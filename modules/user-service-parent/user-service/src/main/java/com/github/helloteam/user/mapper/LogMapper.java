package com.github.helloteam.user.mapper;

import com.github.helloteam.common.basic.model.Log;
import com.github.helloteam.common.core.persistence.CrudMapper;
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
