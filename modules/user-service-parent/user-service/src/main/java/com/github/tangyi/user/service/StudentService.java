package com.github.tangyi.user.service;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.user.api.constant.UserStudentConstant;
import com.github.tangyi.user.api.dto.StudentDto;
import com.github.tangyi.user.api.module.Student;
import com.github.tangyi.user.api.module.UserStudent;
import com.github.tangyi.user.mapper.StudentMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生Service
 *
 * @author zdz
 * @date 2022/04/16 12:16
 */
@AllArgsConstructor
@Service
public class StudentService extends CrudService<StudentMapper, Student> {

    /**
     * 用户service
     */
    private final UserService userService;

    /**
     * 用户学生关联service
     */
    private final UserStudentService userStudentService;

    /**
     * 新增学生
     *
     * @param studentDto 学生数据传输对象实例
     * @return 是否添加成功
     */
    @Transactional
    public int add(StudentDto studentDto) {
        String currentUser = SysUtil.getUser();
        String tenantCode = SysUtil.getTenantCode();
        Long userId = studentDto.getUserId();
        // 查询当前用户
        if (userId != null) {
            UserVo userVo = userService.findUserByIdentifier(currentUser, tenantCode);
            if (userVo == null) {
                throw new CommonException("Get user info failed");
            }
            userId = userVo.getId();
        }
        // 将学生DTO内信息赋值给学生实例
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        student.setCommonValue(currentUser, SysUtil.getSysCode(), tenantCode);
        // 新增用户学生关系
        UserStudent userStudent = new UserStudent();
        userStudent.setCommonValue(currentUser, SysUtil.getSysCode(), tenantCode);
        userStudent.setUserId(userId);
        userStudent.setStudentId(student.getId());
        // 默认关系是父子关系
        if (studentDto.getRelationshipType() == null) {
            userStudent.setRelationshipType(UserStudentConstant.RELATIONSHIP_TYPE_FATHER);
        }
        // 保存新增的学生
        userStudentService.insert(userStudent);
        return this.insert(student);
    }

}
