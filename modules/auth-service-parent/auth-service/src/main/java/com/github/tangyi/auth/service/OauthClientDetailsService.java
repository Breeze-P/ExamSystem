package com.github.tangyi.auth.service;

import com.github.tangyi.auth.api.module.OauthClientDetails;
import com.github.tangyi.auth.mapper.OauthClientDetailsMapper;
import com.github.tangyi.common.core.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * Oauth2客户端Service类
 *
 * @author zdz
 * @date 2022/04/14 11:46
 */
@Service
public class OauthClientDetailsService extends CrudService<OauthClientDetailsMapper, OauthClientDetails> {

}
