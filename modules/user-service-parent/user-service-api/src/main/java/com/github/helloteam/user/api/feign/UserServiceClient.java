package com.github.helloteam.user.api.feign;

import com.github.helloteam.common.basic.model.Log;
import com.github.helloteam.common.basic.vo.AttachmentVo;
import com.github.helloteam.common.basic.vo.DeptVo;
import com.github.helloteam.common.basic.vo.UserVo;
import com.github.helloteam.common.core.constant.ServiceConstant;
import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.feign.config.CustomFeignConfig;
import com.github.helloteam.user.api.dto.UserDto;
import com.github.helloteam.user.api.dto.UserInfoDto;
import com.github.helloteam.user.api.feign.factory.UserServiceClientFallbackFactory;
import com.github.helloteam.user.api.module.Menu;
import com.github.helloteam.user.api.module.Tenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户service客户端接口
 *
 * @author zdz
 * @date 2022/04/15 00:17
 */
@FeignClient(value = ServiceConstant.USER_SERVICE,
        configuration = CustomFeignConfig.class,
        fallbackFactory = UserServiceClientFallbackFactory.class)
public interface UserServiceClient {

    /**
     * 获取当前用户的信息
     *
     * @return 包含用户详细信息的响应Bean
     */
    @GetMapping("/v1/user/info")
    ResponseBean<UserInfoDto> info();

    /**
     * 根据用户名获取用户详细信息
     *
     * @param identifier identifier
     * @param tenantCode 租户标识
     * @return 包含用户详细信息的响应Bean
     */
    @GetMapping("/v1/user/anonymousUser/findUserByIdentifier/{identifier}")
    ResponseBean<UserVo> findUserByIdentifier(@PathVariable("identifier") String identifier,
                                              @RequestParam("tenantCode") String tenantCode);

    /**
     * 根据用户名获取用户详细信息
     *
     * @param identifier   identifier
     * @param identityType identityType
     * @param tenantCode   租户标识
     * @return 包含用户详细信息的响应Bean
     */
    @GetMapping("/v1/user/anonymousUser/findUserByIdentifier/{identifier}")
    ResponseBean<UserVo> findUserByIdentifier(@PathVariable("identifier") String identifier,
                                              @RequestParam(value = "identityType", required = false) Integer identityType,
                                              @RequestParam("tenantCode") String tenantCode);

    /**
     * 根据用户id获取用户
     *
     * @param ids ids
     * @return 包含用户详细信息列表的响应Bean
     */
    @RequestMapping(value = "/v1/user/findById", method = RequestMethod.POST)
    ResponseBean<List<UserVo>> findUserById(@RequestBody Long[] ids);

    /**
     * 根据社交账号获取用户详细信息
     *
     * @param social     social
     * @param tenantCode 租户标识
     * @return 包含用户VO实例的响应Bean
     */
    @GetMapping("/v1/user/findUserBySocial/{social}")
    ResponseBean<UserVo> findUserBySocial(@PathVariable("social") String social, @RequestParam("tenantCode") String tenantCode);

    /**
     * 注册用户
     *
     * @param userDto userDto
     * @return 包含请求处理结果的响应Bean
     */
    @PostMapping("/v1/user/anonymousUser/register")
    ResponseBean<Boolean> registerUser(@RequestBody UserDto userDto);

    /**
     * 查询用户数量
     *
     * @param userVo userVo
     * @return 包含用户数量的响应Bean
     */
    @RequestMapping(value = "/v1/user/userCount", method = RequestMethod.POST)
    ResponseBean<Integer> findUserCount(@RequestBody UserVo userVo);

    /**
     * 根据部门id获取部门
     *
     * @param ids ids
     * @return 包含部门VO实例列表的响应Bean
     */
    @RequestMapping(value = "/v1/dept/findById", method = RequestMethod.POST)
    ResponseBean<List<DeptVo>> findDeptById(@RequestBody Long[] ids);

    /**
     * 根据附件id获取附件
     *
     * @param ids ids
     * @return 包含附件VO实例列表的响应Bean
     */
    @RequestMapping(value = "/v1/attachment/findById", method = RequestMethod.POST)
    ResponseBean<List<AttachmentVo>> findAttachmentById(@RequestBody Long[] ids);

    /**
     * 根据ID删除附件
     *
     * @param id id
     * @return 包含请求处理结果的响应Bean
     */
    @DeleteMapping("/v1/attachment/{id}")
    ResponseBean<Boolean> deleteAttachment(@PathVariable(value = "id") Long id);

    /**
     * 根据角色查找菜单
     *
     * @param role       角色
     * @param tenantCode 租户标识
     * @return 包含菜单实例列表的响应Bean
     */
    @GetMapping("/v1/menu/anonymousUser/findMenuByRole/{role}")
    ResponseBean<List<Menu>> findMenuByRole(@PathVariable("role") String role,
                                            @RequestParam("tenantCode") String tenantCode);

    /**
     * 查询所有菜单
     *
     * @param tenantCode 租户标识
     * @return 包含菜单实例列表的响应Bean
     */
    @GetMapping("/v1/menu/anonymousUser/findAllMenu")
    ResponseBean<List<Menu>> findAllMenu(@RequestParam("tenantCode") String tenantCode);

    /**
     * 根据租户code获取租户的详细信息
     *
     * @param tenantCode 租户标识
     * @return 包含租户实例的响应Bean
     */
    @GetMapping("/v1/tenant/anonymousUser/findTenantByTenantCode/{tenantCode}")
    ResponseBean<Tenant> findTenantByTenantCode(@PathVariable("tenantCode") String tenantCode);

    /**
     * 更新用户登录信息
     *
     * @param userDto userDto
     * @return 包含请求处理结果的响应Bean
     */
    @PutMapping("/v1/user/anonymousUser/updateLoginInfo")
    ResponseBean<Boolean> updateLoginInfo(UserDto userDto);

    /**
     * 保存日志
     *
     * @param log 日志信息
     * @return 包含请求处理结果的响应Bean
     */
    @PostMapping("/v1/log")
    ResponseBean<Boolean> saveLog(@RequestBody Log log);

}
