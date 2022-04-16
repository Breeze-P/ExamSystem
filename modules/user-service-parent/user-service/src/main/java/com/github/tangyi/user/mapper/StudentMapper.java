package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生 mapper
 *
 * @author zdz
 * @date 2022/04/16 12:00
 */
@Mapper
public interface StudentMapper extends CrudMapper<Student> {

}
