package com.github.tangyi.gateway.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.gateway.module.Route;
import org.apache.ibatis.annotations.Mapper;

/**
 * 封装常用路由Crud操作的接口
 *
 * @author zdz
 * @date 2022/04/11 21:44
 */
@Mapper
public interface RouteMapper extends CrudMapper<Route> {

}
