package com.github.helloteam.exam.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 题目分类
 *
 * @author zdz
 * @date 2022/04/16 14:13
 */
@Data
public class SubjectCategory extends BaseEntity<SubjectCategory> {

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    /**
     * 分类描述
     */
    private String categoryDesc;

    /**
     * 父分类id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 类型: 0-私共,1-公有
     */
    private Integer type;

}
