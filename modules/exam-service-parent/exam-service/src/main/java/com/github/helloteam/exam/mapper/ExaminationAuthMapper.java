package com.github.helloteam.exam.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.exam.api.module.ExaminationAuth;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试权限Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:33
 */
@Mapper
public interface ExaminationAuthMapper extends CrudMapper<ExaminationAuth> {
}