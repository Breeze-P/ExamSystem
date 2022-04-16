package com.github.helloteam.exam.service;

import com.github.pagehelper.PageInfo;
import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.exam.api.module.ExaminationSubject;
import com.github.helloteam.exam.mapper.ExaminationSubjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考试题目关联service
 *
 * @author zdz
 * @date 2022/04/16 14:39
 */
@AllArgsConstructor
@Service
public class ExaminationSubjectService extends CrudService<ExaminationSubjectMapper, ExaminationSubject> {

    @Override
    public PageInfo<ExaminationSubject> findPage(PageInfo<ExaminationSubject> page, ExaminationSubject entity) {
        return super.findPage(page, entity);
    }

    /**
     * 根据题目ID删除
     *
     * @param examinationSubject examinationSubject
     * @return int
     */
    @Transactional
    public int deleteBySubjectId(ExaminationSubject examinationSubject) {
        return this.dao.deleteBySubjectId(examinationSubject);
    }

    /**
     * 根据题目ID查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    public List<ExaminationSubject> findListBySubjectId(ExaminationSubject examinationSubject) {
        return this.dao.findListBySubjectId(examinationSubject);
    }

    /**
     * 根据考试id查询题目id列表
     *
     * @param examinationId examinationId
     * @return int
     */
    public List<ExaminationSubject> findListByExaminationId(Long examinationId) {
        return this.dao.findListByExaminationId(examinationId);
    }

    /**
     * 根据考试ID和题目ID查询
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    public ExaminationSubject findByExaminationIdAndSubjectId(ExaminationSubject examinationSubject) {
        return this.dao.findByExaminationIdAndSubjectId(examinationSubject);
    }

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    public ExaminationSubject getByPreviousId(ExaminationSubject examinationSubject) {
        return this.dao.getByPreviousId(examinationSubject);
    }

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    public ExaminationSubject getPreviousByCurrentId(ExaminationSubject examinationSubject) {
        return this.dao.getPreviousByCurrentId(examinationSubject);
    }

    /**
     * 根据分类id查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    public List<ExaminationSubject> findListByCategoryId(ExaminationSubject examinationSubject) {
        return this.dao.findListByCategoryId(examinationSubject);
    }

}
