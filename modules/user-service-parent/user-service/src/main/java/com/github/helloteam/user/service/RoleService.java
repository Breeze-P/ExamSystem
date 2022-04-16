package com.github.helloteam.user.service;

import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.user.api.module.Role;
import com.github.helloteam.user.mapper.RoleMapper;
import com.github.helloteam.user.mapper.RoleMenuMapper;
import com.github.helloteam.user.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色service
 *
 * @author zdz
 * @date 2022/04/16 13:43
 */
@AllArgsConstructor
@Service
public class RoleService extends CrudService<RoleMapper, Role> {

    /**
     * 角色菜单关联
     */
    private final RoleMenuMapper roleMenuMapper;

    /**
     * 用户角色关联
     */
    private final UserRoleMapper userRoleMapper;

    /**
     * 查询所有角色
     *
     * @param role 角色
     * @return 角色列表
     */
    @Override
    @Cacheable(value = "role#" + CommonConstant.CACHE_EXPIRE, key = "#role.applicationCode")
    public List<Role> findAllList(Role role) {
        return super.findAllList(role);
    }

    /**
     * 根据角色code查询角色
     *
     * @param role 角色code
     * @return 角色
     */
    @Cacheable(value = "role#" + CommonConstant.CACHE_EXPIRE, key = "#role.roleCode")
    public Role findByRoleCode(Role role) {
        return this.dao.findByRoleCode(role);
    }

    /**
     * 新增角色
     *
     * @param role 角色
     * @return 是否插入成功
     */
    @Override
    @Transactional
    @CacheEvict(value = "role", key = "#role.roleCode")
    public int insert(Role role) {
        return super.insert(role);
    }

    /**
     * 更新角色信息
     *
     * @param role 角色
     * @return 是否更新成功
     */
    @Override
    @Transactional
    @CacheEvict(value = "role", key = "#role.roleCode")
    public int update(Role role) {
        return super.update(role);
    }

    /**
     * 删除角色
     *
     * @param role 角色
     * @return 是否删除成功
     */
    @Override
    @Transactional
    @CacheEvict(value = "role", key = "#role.roleCode")
    public int delete(Role role) {
        // 删除角色菜单关系
        roleMenuMapper.deleteByRoleId(role.getId());
        // 删除用户角色关系
        userRoleMapper.deleteByRoleId(role.getId());
        return super.delete(role);
    }

}
