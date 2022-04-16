package com.github.tangyi.user.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.user.api.module.User;
import com.github.tangyi.user.api.module.UserAuths;
import com.github.tangyi.user.mapper.UserAuthsMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户授权信息关联service
 *
 * @author zdz
 * @date 2022/04/16 13:50
 */
@AllArgsConstructor
@Slf4j
@Service
public class UserAuthsService extends CrudService<UserAuthsMapper, UserAuths> {

    /**
     * 根据唯一标识查询
     *
     * @param userAuths 用户授权信息关联列表
     * @return 用户授权信息关联
     */
    public UserAuths getByIdentifier(UserAuths userAuths) {
        return this.dao.getByIdentifier(userAuths);
    }

    /**
     * 根据用户批量查询用户权限
     *
     * @param userList 用户列表
     * @return 用户授权信息关联列表
     */
    public List<UserAuths> getListByUsers(List<User> userList) {
        return this.dao.getListByUserIds(userList.stream().map(User::getId).distinct().toArray(Long[]::new));
    }

    /**
     * 根据唯一标识删除
     *
     * @param userAuths userAuths
     * @return 是否操作成功
     */
    @Transactional
    public int deleteByIdentifier(UserAuths userAuths) {
        return this.dao.deleteByIdentifier(userAuths);
    }

    /**
     * 根据用户ID删除
     *
     * @param userAuths userAuths
     * @return 是否操作成功
     */
    @Transactional
    public int deleteByUserId(UserAuths userAuths) {
        return this.dao.deleteByUserId(userAuths);
    }

    /**
     * 批量插入
     *
     * @param userAuths userAuths
     * @return 是否操作成功
     */
    @Transactional
    public int insertBatch(List<UserAuths> userAuths) {
        return dao.insertBatch(userAuths);
    }
}
