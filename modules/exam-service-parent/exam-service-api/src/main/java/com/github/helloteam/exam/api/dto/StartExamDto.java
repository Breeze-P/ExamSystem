package com.github.helloteam.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.helloteam.exam.api.module.Examination;
import com.github.helloteam.exam.api.module.ExaminationRecord;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zdz
 * @date 2022/04/16 14:17
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartExamDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试记录信息
     */
    private ExaminationRecord examRecord;

    /**
     * 考试信息
     */
    private Examination examination;

    /**
     * 题目信息
     */
    private SubjectDto subjectDto;

}
