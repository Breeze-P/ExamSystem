package com.github.helloteam.auth.mapper;

import com.github.helloteam.auth.api.module.OauthClientDetails;
import com.github.helloteam.common.core.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Oauth2客户端mapper接口
 *
 * @author zdz
 * @date 2022/04/14 11:47
 */
@Mapper
public interface OauthClientDetailsMapper extends CrudMapper<OauthClientDetails> {

}
