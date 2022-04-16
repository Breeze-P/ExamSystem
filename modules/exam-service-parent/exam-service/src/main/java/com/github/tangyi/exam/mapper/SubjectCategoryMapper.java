package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.module.SubjectCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目分类Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:34
 */
@Mapper
public interface SubjectCategoryMapper extends CrudMapper<SubjectCategory> {
}
