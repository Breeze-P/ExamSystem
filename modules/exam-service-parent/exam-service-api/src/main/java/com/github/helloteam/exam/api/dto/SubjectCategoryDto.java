package com.github.helloteam.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.helloteam.common.core.persistence.TreeEntity;
import com.github.helloteam.exam.api.module.SubjectCategory;
import lombok.Data;

/**
 * 题目类型DTO
 *
 * @author zdz
 * @date 2022/04/16 14:17
 */
@Data
public class SubjectCategoryDto extends TreeEntity<SubjectCategoryDto> {

    /**
     * 分类名称
     */
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
     * 构造器
     */
    public SubjectCategoryDto(SubjectCategory subjectCategory) {
        this.id = subjectCategory.getId();
        this.categoryName = subjectCategory.getCategoryName();
        this.categoryDesc = subjectCategory.getCategoryDesc();
        this.parentId = subjectCategory.getParentId();
        this.sort = subjectCategory.getSort();
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}