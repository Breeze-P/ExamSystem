package com.github.helloteam.user.api.module;

import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 角色菜单关联
 *
 * @author zdz
 * @date 2022/04/15 00:08
 */
@Data
public class RoleMenu extends BaseEntity<RoleMenu> {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
