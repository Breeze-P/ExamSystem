package com.github.helloteam.user.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.user.api.module.Student;
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
