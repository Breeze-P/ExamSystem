package com.github.helloteam.user.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.user.api.module.Tenant;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户Mapper
 *
 * @author zdz
 * @date 2022/04/16 12:02
 */
@Mapper
public interface TenantMapper extends CrudMapper<Tenant> {

    /**
     * 根据租户标识获取租户信息
     *
     * @param tenantCode 租户code
     * @return 租户实体
     */
    Tenant getByTenantCode(String tenantCode);

    /**
     * 查询租户数量
     *
     * @return 租户数目
     */
    Integer tenantCount();

}
