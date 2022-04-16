package com.github.helloteam.user.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.user.api.module.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单mapper
 *
 * @author zdz
 * @date 2022/04/16 11:59
 */
@Mapper
public interface MenuMapper extends CrudMapper<Menu> {

    /**
     * 根据角色查找菜单
     *
     * @param role       角色标识
     * @param tenantCode 租户标识
     * @return 菜单列表
     */
    List<Menu> findByRole(@Param("role") String role, @Param("tenantCode") String tenantCode);

    /**
     * 批量插入
     *
     * @param menus 菜单列表
     * @return 是否插入成功
     */
    int insertBatch(List<Menu> menus);

    /**
     * 根据租户code删除
     *
     * @param menu 菜单
     * @return 是否删除成功
     */
    int deleteByTenantCode(Menu menu);

}
