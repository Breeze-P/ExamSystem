package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.module.SubjectJudgement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 判断题Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:34
 */
@Mapper
public interface SubjectJudgementMapper extends CrudMapper<SubjectJudgement> {

    /**
     * 物理删除
     *
     * @param subjectJudgement subjectJudgement
     * @return int
     */
    int physicalDelete(SubjectJudgement subjectJudgement);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     */
    int physicalDeleteAll(Long[] ids);

}
