package com.github.tangyi.user.service;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.common.security.constant.SecurityConstant;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.user.api.constant.TenantConstant;
import com.github.tangyi.user.api.enums.IdentityType;
import com.github.tangyi.user.api.module.*;
import com.github.tangyi.user.mapper.TenantMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户service
 *
 * @author zdz
 * @date 2022/04/16 13:48
 */
@Slf4j
@AllArgsConstructor
@Service
public class TenantService extends CrudService<TenantMapper, Tenant> {

    /**
     * 用户service
     */
    private final UserService userService;

    /**
     * 用户授权信息关联service
     */
    private final UserAuthsService userAuthsService;

    /**
     * 用户角色关联service
     */
    private final UserRoleService userRoleService;

    /**
     * 角色service
     */
    private final RoleService roleService;

    /**
     * 菜单service
     */
    private final MenuService menuService;

    /**
     * 根据租户标识获取
     *
     * @param tenantCode tenantCode
     * @return Tenant
     */
    @Cacheable(value = "tenant#" + CommonConstant.CACHE_EXPIRE, key = "#tenantCode")
    public Tenant getByTenantCode(String tenantCode) {
        return this.dao.getByTenantCode(tenantCode);
    }

    /**
     * 新增租户，自动初始化租户管理员账号
     *
     * @param tenant 租户信息
     * @return 是否添加成功
     */
    @Transactional
    @CacheEvict(value = "tenant", key = "#tenant.tenantCode")
    public int add(Tenant tenant) {
        return this.insert(tenant);
    }

    /**
     * 更新租户信息
     *
     * @param tenant 租户信息
     * @return 是否更新成功
     */
    @Transactional
    @CacheEvict(value = "tenant", key = "#tenant.tenantCode")
    @Override
    public int update(Tenant tenant) {
        Integer status = tenant.getStatus();
        Tenant currentTenant = this.get(tenant);
        // 待审核 -> 审核通过
        if (currentTenant != null && currentTenant.getStatus().equals(TenantConstant.PENDING_AUDIT) && status.equals(TenantConstant.APPROVAL)) {
            log.info("Pending review -> review passed：{}", tenant.getTenantCode());
            // 用户基本信息
            User user = new User();
            user.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), tenant.getTenantCode());
            user.setStatus(CommonConstant.STATUS_NORMAL);
            user.setName(tenant.getTenantName());
            userService.insert(user);
            // 用户账号
            UserAuths userAuths = new UserAuths();
            userAuths.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), tenant.getTenantCode());
            userAuths.setUserId(user.getId());
            userAuths.setIdentifier(tenant.getTenantCode());
            userAuths.setIdentityType(IdentityType.PASSWORD.getValue());
            userAuths.setCredential(userService.encodeCredential(CommonConstant.DEFAULT_PASSWORD));
            userAuthsService.insert(userAuths);
            // 绑定角色
            Role role = new Role();
            role.setRoleCode(SecurityConstant.ROLE_TENANT_ADMIN);
            role = roleService.findByRoleCode(role);
            UserRole userRole = new UserRole();
            userRole.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), tenant.getTenantCode());
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoleService.insert(userRole);
        }
        return super.update(tenant);
    }

    /**
     * 删除租户
     *
     * @param tenant 需要删除的租户信息
     * @return 是否删除成功
     */
    @Transactional
    @CacheEvict(value = "tenant", key = "#tenant.tenantCode")
    @Override
    public int delete(Tenant tenant) {
        // 删除菜单
        Menu menu = new Menu();
        menu.setTenantCode(tenant.getTenantCode());
        menuService.deleteByTenantCode(menu);
        // TODO 删除用户
        // TODO 删除权限
        return super.delete(tenant);
    }

    /**
     * 批量删除
     *
     * @param ids IDs
     * @return 是否删除成功
     */
    @Transactional
    @CacheEvict(value = "tenant", allEntries = true)
    @Override
    public int deleteAll(Long[] ids) {
        return super.deleteAll(ids);
    }

    /**
     * 查询单位数量
     *
     * @return 租户数量
     */
    public Integer tenantCount() {
        return this.dao.tenantCount();
    }

}
