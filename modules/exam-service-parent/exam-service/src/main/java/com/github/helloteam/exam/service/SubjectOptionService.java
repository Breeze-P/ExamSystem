package com.github.helloteam.exam.service;

import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.exam.api.module.SubjectOption;
import com.github.helloteam.exam.mapper.SubjectOptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 选择题选项service
 *
 * @author zdz
 * @date 2022/04/16 14:40
 */
@Service
public class SubjectOptionService extends CrudService<SubjectOptionMapper, SubjectOption> {

    /**
     * 查找题目
     *
     * @param subjectOption subjectOption
     * @return SubjectOption
     */
    @Override
    public SubjectOption get(SubjectOption subjectOption) {
        return super.get(subjectOption);
    }

    /**
     * 根据题目ID查找
     *
     * @param subjectOption subjectOption
     * @return List
     */
    public List<SubjectOption> getBySubjectChoicesId(SubjectOption subjectOption) {
        return this.dao.getBySubjectChoicesId(subjectOption);
    }

    /**
     * 新增
     *
     * @param subjectOption subjectOption
     * @return int
     */
    @Override
    @Transactional
    public int insert(SubjectOption subjectOption) {
        return super.insert(subjectOption);
    }

    /**
     * 批量保存
     *
     * @param subjectOptionList subjectOptionList
     * @return int
     */
    @Transactional
    public int insertBatch(List<SubjectOption> subjectOptionList) {
        return this.dao.insertBatch(subjectOptionList);
    }

    /**
     * 更新题目
     *
     * @param subjectOption subjectOption
     * @return int
     */
    @Override
    @Transactional
    public int update(SubjectOption subjectOption) {
        return super.update(subjectOption);
    }

    /**
     * 删除题目
     *
     * @param subjectOption subjectOption
     * @return int
     */
    @Override
    @Transactional
    public int delete(SubjectOption subjectOption) {
        return super.delete(subjectOption);
    }

    /**
     * 根据选择题ID删除
     *
     * @param subjectOption subjectOption
     * @return int
     */
    @Transactional
    public int deleteBySubjectChoicesId(SubjectOption subjectOption) {
        return this.dao.deleteBySubjectChoicesId(subjectOption);
    }

    /**
     * 批量删除题目
     *
     * @param ids ids
     * @return int
     */
    @Override
    @Transactional
    public int deleteAll(Long[] ids) {
        return super.deleteAll(ids);
    }

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     */
    @Transactional
    public int physicalDeleteAll(Long[] ids) {
        return this.dao.physicalDeleteAll(ids);
    }

}
