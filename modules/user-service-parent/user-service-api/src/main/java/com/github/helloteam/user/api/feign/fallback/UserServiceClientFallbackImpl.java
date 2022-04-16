package com.github.helloteam.user.api.feign.fallback;

import com.github.helloteam.common.basic.model.Log;
import com.github.helloteam.common.basic.vo.AttachmentVo;
import com.github.helloteam.common.basic.vo.DeptVo;
import com.github.helloteam.common.basic.vo.UserVo;
import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.user.api.dto.UserDto;
import com.github.helloteam.user.api.dto.UserInfoDto;
import com.github.helloteam.user.api.feign.UserServiceClient;
import com.github.helloteam.user.api.module.Menu;
import com.github.helloteam.user.api.module.Tenant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务断路器实现类
 *
 * @author zdz
 * @date 2022/04/15 00:26
 */
@Slf4j
@Component
public class UserServiceClientFallbackImpl implements UserServiceClient {

    /**
     * Throwable
     */
    private Throwable throwable;

    /**
     * 查询当前登录的用户信息
     *
     * @return 包含用户详细信息DTO实例的响应Bean
     */
    @Override
    public ResponseBean<UserInfoDto> info() {
        log.error("Feign get user info failed", throwable);
        return null;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param identifier identifier
     * @param tenantCode 租户标识
     * @return 包含用户VO实例的响应Bean
     */
    @Override
    public ResponseBean<UserVo> findUserByIdentifier(String identifier, String tenantCode) {
        log.error("Feign findUserByIdentifier failed: {}, {}, {}", tenantCode, identifier, throwable);
        return null;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param identifier   identifier
     * @param identityType identityType
     * @param tenantCode   租户标识
     * @return 包含用户VO实例的响应Bean
     */
    @Override
    public ResponseBean<UserVo> findUserByIdentifier(String identifier, Integer identityType, String tenantCode) {
        log.error("Feign findUserByIdentifier failed: {}, {}, {}, {}", tenantCode, identityType, identifier, throwable);
        return null;
    }

    /**
     * 根据用户ID批量查询用户信息
     *
     * @param ids ids
     * @return 包含用户VO实例列表的响应Bean
     */
    @Override
    public ResponseBean<List<UserVo>> findUserById(@RequestBody Long[] ids) {
        log.error("Call findUserById error: {}", ids, throwable);
        return null;
    }

    /**
     * 根据社交账号获取用户详细信息
     *
     * @param social     social
     * @param tenantCode 租户标识
     * @return 包含用户VO实例的响应Bean
     */
    @Override
    public ResponseBean<UserVo> findUserBySocial(String social, String tenantCode) {
        log.error("Feign findUserBySocial failed, {}, {}, {}", social, tenantCode, throwable);
        return null;
    }

    /**
     * 注册用户
     *
     * @param userDto userDto
     * @return 包含请求处理结果的响应Bean
     */
    @Override
    public ResponseBean<Boolean> registerUser(UserDto userDto) {
        log.error("Feign registerUser failed, {}, {}, {}", userDto.getIdentityType(), userDto.getIdentifier(), throwable);
        return null;
    }

    /**
     * 查询用户数量
     *
     * @param userVo userVo
     * @return 包含用户数量的响应Bean
     */
    @Override
    public ResponseBean<Integer> findUserCount(UserVo userVo) {
        log.error("Call findUserCount error: {}", userVo, throwable);
        return new ResponseBean<>(0);
    }

    /**
     * 根据部门ID批量查询部门信息
     *
     * @param ids ids
     * @return 包含部门VO实例列表的响应Bean
     */
    @Override
    public ResponseBean<List<DeptVo>> findDeptById(@RequestBody Long[] ids) {
        log.error("Call findDeptById error: {}", ids, throwable);
        return null;
    }

    /**
     * 根据附件ID批量查询附件信息
     *
     * @param ids ids
     * @return 包含附件实例列表的响应Bean
     */
    @Override
    public ResponseBean<List<AttachmentVo>> findAttachmentById(Long[] ids) {
        log.error("Call findAttachmentById error: {}", ids, throwable);
        return new ResponseBean<>(new ArrayList<>());
    }

    /**
     * 根据附件ID删除附件
     *
     * @param id id
     * @return 包含请求处理结果的响应Bean
     */
    @Override
    public ResponseBean<Boolean> deleteAttachment(Long id) {
        log.error("Call deleteAttachment error: {}", id, throwable);
        return new ResponseBean<>(Boolean.FALSE);
    }

    /**
     * 根据角色查找菜单
     *
     * @param tenantCode 租户标识
     * @param role       角色
     * @return 包含菜单实例列表的响应Bean
     */
    @Override
    public ResponseBean<List<Menu>> findMenuByRole(String role, String tenantCode) {
        log.error("Feign findMenuByRole failed, {}, {}", tenantCode, throwable);
        return new ResponseBean<>(new ArrayList<>());
    }

    /**
     * 查询所有菜单
     *
     * @param tenantCode 租户标识
     * @return 包含菜单实例列表的响应Bean
     */
    @Override
    public ResponseBean<List<Menu>> findAllMenu(String tenantCode) {
        log.error("Feign findAllMenu failed, {}, {}", tenantCode, throwable);
        return new ResponseBean<>(new ArrayList<>());
    }

    /**
     * 根据租户标识查询租户详细信息
     *
     * @param tenantCode 租户标识
     * @return 包含租户实例的响应Bean
     */
    @Override
    public ResponseBean<Tenant> findTenantByTenantCode(String tenantCode) {
        log.error("Feign findTenantByTenantCode failed, {}, {}", tenantCode, throwable);
        return null;
    }

    /**
     * 更新用户登录信息
     *
     * @param userDto userDto
     * @return 包含请求处理结果的响应Bean
     */
    @Override
    public ResponseBean<Boolean> updateLoginInfo(UserDto userDto) {
        log.error("Feign updateLoginInfo failed, {}, {}, {}", userDto.getIdentityType(), userDto.getIdentifier(), throwable);
        return null;
    }

    /**
     * 保存日志
     *
     * @param logInfo logInfo
     * @return 包含请求处理结果的响应Bean
     */
    @Override
    public ResponseBean<Boolean> saveLog(Log logInfo) {
        log.error("Feign save log error", throwable);
        return null;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
