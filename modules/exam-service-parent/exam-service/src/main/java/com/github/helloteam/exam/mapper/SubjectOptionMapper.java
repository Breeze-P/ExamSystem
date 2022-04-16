package com.github.helloteam.exam.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.exam.api.module.SubjectOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 选择题Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:34
 */
@Mapper
public interface SubjectOptionMapper extends CrudMapper<SubjectOption> {

    /**
     * 根据题目ID查找
     *
     * @param subjectOption subjectOption
     * @return List
     */
    List<SubjectOption> getBySubjectChoicesId(SubjectOption subjectOption);

    /**
     * 批量保存
     *
     * @param subjectOptionList subjectOptionList
     * @return int
     */
    int insertBatch(List<SubjectOption> subjectOptionList);

    /**
     * 根据选择题ID删除
     *
     * @param subjectOption subjectOption
     * @return int
     */
    int deleteBySubjectChoicesId(SubjectOption subjectOption);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     */
    int physicalDeleteAll(Long[] ids);

}
