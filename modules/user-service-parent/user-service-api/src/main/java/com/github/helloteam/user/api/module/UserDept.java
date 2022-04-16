package com.github.helloteam.user.api.module;

import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 用户部门关系
 *
 * @author zdz
 * @date 2022/04/15 00:06
 */
@Data
public class UserDept extends BaseEntity<UserDept> {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 部门ID
     */
    private String deptId;

}
