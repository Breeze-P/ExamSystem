package com.github.helloteam.common.basic.vo;

import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 角色 VO类
 *
 * @author zdz
 * @date 2022/04/10 14:55
 */
@Data
public class RoleVo extends BaseEntity<RoleVo> {

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色对应code
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String roleDesc;

}
