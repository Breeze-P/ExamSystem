package com.github.helloteam.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.helloteam.common.core.persistence.BaseEntity;
import com.github.helloteam.exam.api.module.Answer;
import com.github.helloteam.exam.api.module.SubjectOption;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 题目DTO
 *
 * @author zdz
 * @date 2022/04/16 14:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto extends BaseEntity<SubjectDto> {

    /**
     * 考试ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationId;

    /**
     * 考试记录ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationRecordId;

    /**
     * 题目分类ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目类型
     */
    private Integer type;

    /**
     * 选择题类型
     */
    private Integer choicesType;

    /**
     * 分值
     */
    private Double score;

    /**
     * 解析
     */
    private String analysis;

    /**
     * 难度等级
     */
    private Integer level;

    /**
     * 答题
     */
    private Answer answer;

    /**
     * 选项列表
     */
    private List<SubjectOption> options;

}
