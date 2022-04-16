package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色菜单关联mapper
 *
 * @author zdz
 * @date 2022/04/16 12:00
 */
@Mapper
public interface RoleMenuMapper extends CrudMapper<RoleMenu> {

    /**
     * 根据角色ID删除
     *
     * @param roleId 角色ID
     * @return 是否删除成功
     */
    int deleteByRoleId(Long roleId);

    /**
     * 根据菜单ID删除
     *
     * @param menuId 菜单ID
     * @return 是否删除成功
     */
    int deleteByMenuId(Long menuId);

    /**
     * 批量保存
     *
     * @param roleMenus 角色菜单关联列表
     * @return 是否插入成功
     */
    int insertBatch(List<RoleMenu> roleMenus);

    /**
     * 根据roleId查询
     *
     * @param roleMenu 角色菜单关联
     * @return 角色菜单关联列表
     */
    List<RoleMenu> getByRoleId(RoleMenu roleMenu);

    /**
     * 根据menuId查询
     *
     * @param roleMenu 角色菜单关联
     * @return 角色菜单关联列表
     */
    List<RoleMenu> getByMenuId(RoleMenu roleMenu);

    /**
     * 根据menuId列表查询
     *
     * @param roleMenus 角色菜单关联列表
     * @return 角色菜单关联列表
     */
    List<RoleMenu> getByMenuIds(List<RoleMenu> roleMenus);

}
