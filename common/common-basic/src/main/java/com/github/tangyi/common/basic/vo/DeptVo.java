package com.github.tangyi.common.basic.vo;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 部门 VO类
 *
 * @author zdz
 * @date 2022/04/10 14:54
 */
@Data
public class DeptVo extends BaseEntity<DeptVo> {

    /**
     * 部门名称
     */
    private String deptName;

}
