package com.github.helloteam.user.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.user.api.module.UserStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户学生关联 mapper
 *
 * @author zdz
 * @date 2022/04/16 12:05
 */
@Mapper
public interface UserStudentMapper extends CrudMapper<UserStudent> {

    /**
     * 根据userId查询
     *
     * @param userId 用户ID
     * @return 用户学生关联列表
     */
    List<UserStudent> getByUserId(String userId);

    /**
     * 根据studentId查询
     *
     * @param studentId 学生ID
     * @return 用户学生关联
     */
    UserStudent getByStudentId(String studentId);

    /**
     * 根据用户id删除
     *
     * @param userId 用户ID
     * @return 是否删除成功
     */
    int deleteByUserId(String userId);

    /**
     * 根据学生id删除
     *
     * @param studentId 学生ID
     * @return 是否删除成功
     */
    int deleteByStudentId(String studentId);

}
