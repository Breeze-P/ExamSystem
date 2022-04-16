package com.github.helloteam.exam.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.exam.api.module.Examination;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:33
 */
@Mapper
public interface ExaminationMapper extends CrudMapper<Examination> {

    /**
     * 查询考试数量
     *
     * @param examination examination
     * @return int
     */
    int findExaminationCount(Examination examination);

    /**
     * 查询参与考试人数
     *
     * @param examination examination
     * @return int
     */
    int findExamUserCount(Examination examination);
}
