package com.github.helloteam.user.api.module;

import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 用户角色关系
 *
 * @author zdz
 * @date 2022/04/15 00:06
 */
@Data
public class UserRole extends BaseEntity<UserRole> {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
