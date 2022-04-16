package com.github.tangyi.user.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.user.api.module.UserRole;
import com.github.tangyi.user.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色关联service
 *
 * @author zdz
 * @date 2022/04/16 12:15
 */
@AllArgsConstructor
@Service
public class UserRoleService extends CrudService<UserRoleMapper, UserRole> {

    /**
     * 用户角色mapper
     */
    private final UserRoleMapper userRoleMapper;

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 用户角色关联列表
     */
    public List<UserRole> getByUserId(Long userId) {
        return userRoleMapper.getByUserId(userId);
    }

    /**
     * 根据用户ID查询
     *
     * @param userIds 用户ID
     * @return 用户角色关联列表
     */
    public List<UserRole> getByUserIds(List<Long> userIds) {
        return userRoleMapper.getByUserIds(userIds);
    }

    /**
     * 批量插入
     *
     * @param userRoles 用户角色关联列表
     * @return 是否插入成功
     */
    public int insertBatch(List<UserRole> userRoles) {
        return userRoleMapper.insertBatch(userRoles);
    }

}
