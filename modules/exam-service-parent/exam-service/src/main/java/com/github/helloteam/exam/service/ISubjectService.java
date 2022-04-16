package com.github.helloteam.exam.service;

import com.github.pagehelper.PageInfo;
import com.github.helloteam.exam.api.dto.SubjectDto;

import java.util.List;

/**
 * 题目通用接口
 *
 * @author zdz
 * @date 2022/04/16 14:37
 */
public interface ISubjectService {

    /**
     * 根据ID查询
     *
     * @param id id
     * @return SubjectDto
     */
    SubjectDto getSubject(Long id);

    /**
     * 根据ID查询上一题、下一题
     *
     * @param examinationId examinationId
     * @param previousId    previousId
     * @param nextType      -1：当前题目，0：下一题，1：上一题
     * @return SubjectDto
     */
    SubjectDto getNextByCurrentIdAndType(Long examinationId, Long previousId, Integer nextType);

    /**
     * 查询题目列表
     *
     * @param subjectDto subjectDto
     * @return List
     */
    List<SubjectDto> findSubjectList(SubjectDto subjectDto);

    /**
     * 查询题目分页列表
     *
     * @param pageInfo   pageInfo
     * @param subjectDto subjectDto
     * @return List
     */
    PageInfo<SubjectDto> findSubjectPage(PageInfo pageInfo, SubjectDto subjectDto);

    /**
     * 根据ID批量查询
     *
     * @param ids ids
     * @return List
     */
    List<SubjectDto> findSubjectListById(Long[] ids);

    /**
     * 保存
     *
     * @param subjectDto subjectDto
     * @return int
     */
    int insertSubject(SubjectDto subjectDto);

    /**
     * 更新
     *
     * @param subjectDto subjectDto
     * @return int
     */
    int updateSubject(SubjectDto subjectDto);

    /**
     * 删除
     *
     * @param subjectDto subjectDto
     * @return int
     */
    int deleteSubject(SubjectDto subjectDto);

    /**
     * 批量删除
     *
     * @param ids ids
     * @return int
     */
    int deleteAllSubject(Long[] ids);

    /**
     * 物理删除
     *
     * @param subjectDto subjectDto
     * @return int
     */
    int physicalDeleteSubject(SubjectDto subjectDto);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     */
    int physicalDeleteAllSubject(Long[] ids);

}
