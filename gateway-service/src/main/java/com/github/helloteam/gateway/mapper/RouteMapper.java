package com.github.helloteam.gateway.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.gateway.module.Route;
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
