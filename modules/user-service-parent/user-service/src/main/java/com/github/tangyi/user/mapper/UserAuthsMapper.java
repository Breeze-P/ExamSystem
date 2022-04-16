package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.UserAuths;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户授权信息关联mapper
 *
 * @author zdz
 * @date 2022/04/16 12:04
 */
@Mapper
public interface UserAuthsMapper extends CrudMapper<UserAuths> {

    /**
     * 根据用户唯一标识查询
     *
     * @param userAuths 用户授权信息
     * @return 用户授权信息关联
     */
    UserAuths getByIdentifier(UserAuths userAuths);

    /**
     * 根据用户id批量查询
     *
     * @param userIds 用户ID
     * @return 用户授权信息关联列表
     */
    List<UserAuths> getListByUserIds(@Param("userIds") Long[] userIds);

    /**
     * 根据唯一标识删除
     *
     * @param userAuths 用户授权信息
     * @return 是否删除成功
     */
    int deleteByIdentifier(UserAuths userAuths);


    /**
     * 根据用户ID删除
     *
     * @param userAuths 用户授权信息
     * @return 是否删除成功
     */
    int deleteByUserId(UserAuths userAuths);

    /**
     * 批量插入
     *
     * @param userAuths 用户授权信息
     * @return 是否插入成功
     */
    int insertBatch(List<UserAuths> userAuths);

}
