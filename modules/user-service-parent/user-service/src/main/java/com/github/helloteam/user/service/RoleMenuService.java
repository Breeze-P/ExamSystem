package com.github.helloteam.user.service;

import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.common.core.utils.IdGen;
import com.github.helloteam.user.api.module.RoleMenu;
import com.github.helloteam.user.mapper.RoleMenuMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单关联service
 *
 * @author zdz
 * @date 2022/04/16 13:43
 */
@AllArgsConstructor
@Service
public class RoleMenuService extends CrudService<RoleMenuMapper, RoleMenu> {

    /**
     * 角色菜单关联mapper
     */
    private final RoleMenuMapper roleMenuMapper;

    /**
     * 保存角色菜单关联
     *
     * @param role  角色
     * @param menus 菜单ID集合
     * @return 是否保存成功
     */
    @Transactional
    @CacheEvict(value = "menu", allEntries = true)
    public int saveRoleMenus(Long role, List<Long> menus) {
        int update = -1;
        if (CollectionUtils.isNotEmpty(menus)) {
            // 删除旧的管理数据
            roleMenuMapper.deleteByRoleId(role);
            List<RoleMenu> roleMenus = new ArrayList<>();
            // 添加新的数据
            for (Long menuId : menus) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setId(IdGen.snowflakeId());
                roleMenu.setRoleId(role);
                roleMenu.setMenuId(menuId);
                roleMenus.add(roleMenu);
            }
            update = roleMenuMapper.insertBatch(roleMenus);
        }
        return update;
    }

    /**
     * 批量保存
     *
     * @param roleMenus roleMenus
     * @return int
     */
    @Transactional
    public int insertBatch(List<RoleMenu> roleMenus) {
        return roleMenuMapper.insertBatch(roleMenus);
    }

    /**
     * 根据roleId查询角色菜单关联列表
     *
     * @param roleMenu 角色菜单关联
     * @return 角色菜单关联列表
     */
    public List<RoleMenu> getByRoleId(RoleMenu roleMenu) {
        return roleMenuMapper.getByRoleId(roleMenu);
    }

    /**
     * 根据menuId查询角色菜单关联列表
     *
     * @param roleMenu 角色菜单关联
     * @return 角色菜单关联列表
     */
    public List<RoleMenu> getByMenuId(RoleMenu roleMenu) {
        return roleMenuMapper.getByMenuId(roleMenu);
    }

    /**
     * 根据menuId列表查询角色菜单关联列表
     *
     * @param roleMenus 角色菜单关联列表
     * @return 角色菜单关联列表
     */
    public List<RoleMenu> getByMenuIds(List<RoleMenu> roleMenus) {
        return roleMenuMapper.getByMenuIds(roleMenus);
    }

}
