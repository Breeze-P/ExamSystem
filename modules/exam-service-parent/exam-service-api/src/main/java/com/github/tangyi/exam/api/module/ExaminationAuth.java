package com.github.tangyi.exam.api.module;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 考试权限
 *
 * @author zdz
 * @date 2022/04/16 14:12
 */
@Data
public class ExaminationAuth extends BaseEntity<ExaminationAuth> {

    /**
     * 学生ID
     */
    private String userId;

    /**
     * 考试ID
     */
    private String examinationId;

}
