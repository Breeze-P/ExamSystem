package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色关联 mapper
 *
 * @author zdz
 * @date 2022/04/16 12:05
 */
@Mapper
public interface UserRoleMapper extends CrudMapper<UserRole> {

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 用户角色关联
     */
    List<UserRole> getByUserId(Long userId);

    /**
     * 根据用户ID批量查询
     *
     * @param userIds 用户ID集合
     * @return 用户角色关联
     */
    List<UserRole> getByUserIds(List<Long> userIds);


    /**
     * 根据用户ID删除
     *
     * @param userId 用户ID
     * @return 是否删除成功
     */
    int deleteByUserId(Long userId);

    /**
     * 根据角色ID删除
     *
     * @param roleId 角色ID
     * @return 是否删除成功
     */
    int deleteByRoleId(Long roleId);

    /**
     * 批量插入
     *
     * @param userRoles 用户角色关系
     * @return 是否插入成功
     */
    int insertBatch(List<UserRole> userRoles);

}
