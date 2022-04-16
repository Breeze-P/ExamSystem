package com.github.helloteam.auth.service;

import com.github.helloteam.auth.api.module.OauthClientDetails;
import com.github.helloteam.auth.mapper.OauthClientDetailsMapper;
import com.github.helloteam.common.core.service.CrudService;
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
