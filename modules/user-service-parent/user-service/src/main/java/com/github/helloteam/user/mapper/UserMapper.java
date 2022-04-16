package com.github.helloteam.user.mapper;

import com.github.helloteam.common.basic.vo.UserVo;
import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.user.api.module.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 mapper
 *
 * @author zdz
 * @date 2022/04/16 12:01
 */
@Mapper
public interface UserMapper extends CrudMapper<User> {

    /**
     * 查询用户数量
     *
     * @param userVo 用户VO类
     * @return 用户数量
     */
    Integer userCount(UserVo userVo);

}
