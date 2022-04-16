package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色mapper
 *
 * @author zdz
 * @date 2022/04/16 11:58
 */
@Mapper
public interface RoleMapper extends CrudMapper<Role> {

    /**
     * 根据角色code查询
     *
     * @param role 角色信息
     * @return 角色
     */
    Role findByRoleCode(Role role);

}
