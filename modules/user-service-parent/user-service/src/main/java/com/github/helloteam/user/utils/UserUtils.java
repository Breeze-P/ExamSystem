package com.github.helloteam.user.utils;

import com.github.helloteam.common.basic.properties.SysProperties;
import com.github.helloteam.common.basic.vo.RoleVo;
import com.github.helloteam.common.core.utils.SpringContextHolder;
import com.github.helloteam.user.api.dto.UserInfoDto;
import com.github.helloteam.user.api.module.Role;
import com.github.helloteam.user.api.module.User;
import com.github.helloteam.user.api.module.UserAuths;
import com.github.helloteam.user.excel.model.UserExcelModel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户工具类
 *
 * @author zdz
 * @date 2022/04/16 11:32
 */
public class UserUtils {

    /**
     * 私有化无参构造器
     */
    private UserUtils() {
    }

    /**
     * 将用户信息转换成excelModel对象
     *
     * @param userInfoDtos 用户信息数据传输对象实例
     * @return 包含用户信息的excelModel对象列表
     */
    public static List<UserExcelModel> convertToExcelModel(List<UserInfoDto> userInfoDtos) {
        List<UserExcelModel> userExcelModels = new ArrayList<>(userInfoDtos.size());
        // 通过转换器逐个将用户DTO实例转化成ExcelModel对象，并存入到列表中
        userInfoDtos.forEach(userInfoDto -> {
            UserExcelModel userExcelModel = new UserExcelModel();
            BeanUtils.copyProperties(userInfoDto, userExcelModel);
            userExcelModels.add(userExcelModel);
        });
        return userExcelModels;
    }

    /**
     * 将Role转化成RoleVo实例
     *
     * @param roles 用户角色属性
     * @return 包含一系列用户VO实例的列表
     */
    public static List<RoleVo> rolesToVo(List<Role> roles) {
        return roles.stream().map(role -> {
            RoleVo roleVo = new RoleVo();
            roleVo.setRoleCode(role.getRoleCode());
            roleVo.setRoleName(role.getRoleName());
            roleVo.setRoleDesc(role.getRoleDesc());
            return roleVo;
        }).collect(Collectors.toList());
    }

    /**
     * 通过将用户实体和用户授权类型的信息赋值给用户DTO实例，从而实现转DTO转化
     *
     * @param userInfoDto 用户信息DTO类
     * @param user        用户实体
     * @param userAuths   用户授权类型
     * @return 用户信息DTO类
     */
    public static void toUserInfoDto(UserInfoDto userInfoDto, User user, UserAuths userAuths) {
        BeanUtils.copyProperties(userAuths, userInfoDto);
        BeanUtils.copyProperties(user, userInfoDto);
    }

    /**
     * 判断当前用户是否为管理员
     *
     * @param identifier 用户唯一标识信息
     * @return 当前用户是否为管理员
     */
    public static boolean isAdmin(String identifier) {
        // 根据系统上下文的相关信息判断当前用户是否为管理员
        SysProperties sysProperties = SpringContextHolder
                .getApplicationContext()
                .getBean(SysProperties.class);
        return identifier.equals(sysProperties.getAdminUser());
    }

}
