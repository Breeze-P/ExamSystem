package com.github.helloteam.exam.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.exam.api.module.ExaminationSubject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 考试题目关联mapper
 *
 * @author zdz
 * @date 2022/04/16 14:33
 */
@Mapper
public interface ExaminationSubjectMapper extends CrudMapper<ExaminationSubject> {

    /**
     * 根据题目ID删除
     *
     * @param examinationSubject examinationSubject
     * @return int
     */
    int deleteBySubjectId(ExaminationSubject examinationSubject);

    /**
     * 根据题目ID查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    List<ExaminationSubject> findListBySubjectId(ExaminationSubject examinationSubject);

    /**
     * 根据考试id查询题目id列表
     *
     * @param examinationId examinationId
     * @return List
     */
    List<ExaminationSubject> findListByExaminationId(Long examinationId);

    /**
     * 根据考试ID和题目序号查询
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    ExaminationSubject findByExaminationIdAndSubjectId(ExaminationSubject examinationSubject);

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    ExaminationSubject getByPreviousId(ExaminationSubject examinationSubject);

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    ExaminationSubject getPreviousByCurrentId(ExaminationSubject examinationSubject);

    /**
     * 根据分类id查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    List<ExaminationSubject> findListByCategoryId(ExaminationSubject examinationSubject);

}
