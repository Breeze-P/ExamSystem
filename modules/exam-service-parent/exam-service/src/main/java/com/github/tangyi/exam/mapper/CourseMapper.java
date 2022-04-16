package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.module.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:33
 */
@Mapper
public interface CourseMapper extends CrudMapper<Course> {
}
