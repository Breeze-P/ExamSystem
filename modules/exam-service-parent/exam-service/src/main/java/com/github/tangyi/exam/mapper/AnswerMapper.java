package com.github.tangyi.exam.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.exam.api.module.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 答题Mapper
 *
 * @author zdz
 * @date 2022/04/16 14:31
 */
@Mapper
public interface AnswerMapper extends CrudMapper<Answer> {

    /**
     * 根据用户ID、考试ID、考试记录ID、题目ID查找答题
     *
     * @param answer answer
     * @return Answer
     */
    Answer getAnswer(Answer answer);

    /**
     * 根据examRecordId查询
     * @param examRecordId examRecordId
     * @return List
     */
    List<Answer> findListByExamRecordId(Long examRecordId);

}
