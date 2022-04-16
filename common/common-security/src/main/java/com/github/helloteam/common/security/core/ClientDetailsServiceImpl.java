package com.github.helloteam.common.security.core;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * jdbc客户端service
 *
 * @author zdz
 * @date 2022/04/11 20:08
 */
public class ClientDetailsServiceImpl extends JdbcClientDetailsService {

    /**
     * 绑定数据源
     * @param dataSource 将要绑定的数据源
     */
    public ClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写方法
     *
     * @param clientId clientId
     * @return ClientDetails
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }

}
