package com.github.helloteam.user.service;

import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.user.api.module.UserStudent;
import com.github.helloteam.user.mapper.UserStudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 用户学生关联service
 *
 * @author zdz
 * @date 2022/04/16 12:14
 */
@Service
public class UserStudentService extends CrudService<UserStudentMapper, UserStudent> {

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 用户学生关联列表
     */
    public List<UserStudent> getByUserId(@NotBlank String userId) {
        return this.dao.getByUserId(userId);
    }

    /**
     * 根据学生ID查询
     *
     * @param studentId 学生ID
     * @return 用户学生关联
     */
    public UserStudent getByStudentId(@NotBlank String studentId) {
        return this.dao.getByStudentId(studentId);
    }

    /**
     * 根据用户id删除
     *
     * @param userId 用户ID
     * @return 是否删除成功
     */
    @Transactional
    public int deleteByUserId(@NotBlank String userId) {
        return this.dao.deleteByUserId(userId);
    }

    /**
     * 根据学生id删除
     *
     * @param studentId 学生ID
     * @return 是否删除成功
     */
    @Transactional
    public int deleteByStudentId(@NotBlank String studentId) {
        return this.dao.deleteByStudentId(studentId);
    }

}
