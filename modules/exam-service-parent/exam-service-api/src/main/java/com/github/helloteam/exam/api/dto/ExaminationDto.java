package com.github.helloteam.exam.api.dto;

import com.github.helloteam.exam.api.module.Course;
import com.github.helloteam.exam.api.module.Examination;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考试DTO
 *
 * @author zdz
 * @date 2022/04/16 14:15
 */
@Data
@NoArgsConstructor
public class ExaminationDto extends Examination {

    /**
     * 课程
     */
    private Course course;

    /**
     * 封面地址
     */
    private String logoUrl;

}
