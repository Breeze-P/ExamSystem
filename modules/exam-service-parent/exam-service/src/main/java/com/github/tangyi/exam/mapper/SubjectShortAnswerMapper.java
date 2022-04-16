package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.module.SubjectShortAnswer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 简答题Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:35
 */
@Mapper
public interface SubjectShortAnswerMapper extends CrudMapper<SubjectShortAnswer> {

    /**
     * 物理删除
     *
     * @param subjectShortAnswer subjectShortAnswer
     * @return int
     */
    int physicalDelete(SubjectShortAnswer subjectShortAnswer);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     */
    int physicalDeleteAll(Long[] ids);

}
