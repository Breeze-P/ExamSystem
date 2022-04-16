package com.github.tangyi.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 答题卡DTO
 *
 * @author zdz
 * @date 2022/04/16 14:14
 */
@Data
public class AnswerCartDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目IDs
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> subjectIds;

}
