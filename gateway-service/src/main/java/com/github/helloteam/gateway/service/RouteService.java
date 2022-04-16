package com.github.helloteam.gateway.service;

import com.fasterxml.jackson.databind.JavaType;
import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.exceptions.CommonException;
import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.common.core.utils.JsonMapper;
import com.github.helloteam.gateway.constants.GatewayConstant;
import com.github.helloteam.gateway.mapper.RouteMapper;
import com.github.helloteam.gateway.module.Route;
import com.github.helloteam.gateway.vo.RouteFilterVo;
import com.github.helloteam.gateway.vo.RoutePredicateVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 路由service
 *
 * @author zdz
 * @date 2022/04/11 21:41
 */
@Slf4j
@AllArgsConstructor
@Service
public class RouteService extends CrudService<RouteMapper, Route> {

    /**
     * 动态路由service
     */
    private final DynamicRouteService dynamicRouteService;

    /**
     * Redis模板
     */
    private final RedisTemplate redisTemplate;

    /**
     * 新增路由
     *
     * @param route 路由信息
     * @return int
     */
    @Override
    public int insert(Route route) {
        int update;
        if (StringUtils.isBlank(route.getRouteId()))
            throw new CommonException("Service id is empty");
        // 校验服务路由是否存在
        Route condition = new Route();
        condition.setRouteId(route.getRouteId());
        List<Route> routes = this.findList(condition);
        if (CollectionUtils.isNotEmpty(routes))
            throw new CommonException("Service id is exits");
        route.setCommonValue("", GatewayConstant.SYS_CODE, GatewayConstant.DEFAULT_TENANT_CODE);
        if ((update = this.dao.insert(route)) > 0) {
            dynamicRouteService.add(routeDefinition(route));
        }
        return update;
    }

    /**
     * 更新路由
     *
     * @param route 路由信息
     * @return int
     */
    @Override
    public int update(Route route) {
        int update;
        if (StringUtils.isBlank(route.getRouteId()))
            throw new CommonException("Service id is empty");
        route.setNewRecord(false);
        route.setCommonValue("", GatewayConstant.SYS_CODE, GatewayConstant.DEFAULT_TENANT_CODE);
        if ((update = this.dao.update(route)) > 0) {
            dynamicRouteService.update(routeDefinition(route));
        }
        return update;
    }

    /**
     * 删除路由
     *
     * @param id id
     * @return Mono
     */
    @Transactional
    public int delete(Long id) {
        Route route = new Route();
        route.setId(id);
        route.setNewRecord(false);
        route.setCommonValue("", GatewayConstant.SYS_CODE, GatewayConstant.DEFAULT_TENANT_CODE);
        int update = this.dao.delete(route);
        dynamicRouteService.delete(id);
        return update;
    }

    /**
     * 刷新路由
     */
    public void refresh() {
        Route init = new Route();
        init.setStatus(CommonConstant.DEL_FLAG_NORMAL.toString());
        List<Route> routes = this.findList(init);
        if (CollectionUtils.isNotEmpty(routes)) {
            log.info("Init {} route records", routes.size());
            for (Route route : routes)
                dynamicRouteService.update(routeDefinition(route));
            // 存入Redis
            redisTemplate.opsForValue().set(CommonConstant.ROUTE_KEY, JsonMapper.getInstance().toJson(routes));
        }
    }

    /**
     * 初始化RouteDefinition
     *
     * @param route 路由信息
     * @return RouteDefinition
     */
    private RouteDefinition routeDefinition(Route route) {
        RouteDefinition routeDefinition = new RouteDefinition();
        // id
        routeDefinition.setId(route.getRouteId());
        // predicates
        if (StringUtils.isNotBlank(route.getPredicates())) {
            routeDefinition.setPredicates(predicateDefinitions(route));
        }
        // filters
        if (StringUtils.isNotBlank(route.getFilters())) {
            routeDefinition.setFilters(filterDefinitions(route));
        }
        // uri
        routeDefinition.setUri(URI.create(route.getUri()));
        return routeDefinition;
    }

    /**
     * 初始化PredicateDefinition
     *
     * @param route 路由信息
     * @return List of PredicateDefinition
     */
    private List<PredicateDefinition> predicateDefinitions(Route route) {
        List<PredicateDefinition> predicateDefinitions = new ArrayList<>();
        try {
            List<RoutePredicateVo> routePredicateVoList = JsonMapper.getInstance().fromJson(
                    route.getPredicates(),
                    JsonMapper.getInstance().createCollectionType(ArrayList.class, RoutePredicateVo.class));
            if (CollectionUtils.isNotEmpty(routePredicateVoList)) {
                for (RoutePredicateVo routePredicateVo : routePredicateVoList) {
                    PredicateDefinition predicate = new PredicateDefinition();
                    predicate.setArgs(routePredicateVo.getArgs());
                    predicate.setName(routePredicateVo.getName());
                    predicateDefinitions.add(predicate);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return predicateDefinitions;
    }

    /**
     * 初始化FilterDefinition
     *
     * @param route 路由信息
     * @return List
     */
    private List<FilterDefinition> filterDefinitions(Route route) {
        List<FilterDefinition> filterDefinitions = new ArrayList<>();
        try {
            JavaType javaType = JsonMapper.getInstance().createCollectionType(ArrayList.class, RouteFilterVo.class);
            List<RouteFilterVo> gatewayFilterDefinitions = JsonMapper.getInstance().fromJson(route.getFilters(), javaType);
            if (CollectionUtils.isNotEmpty(gatewayFilterDefinitions)) {
                for (RouteFilterVo gatewayFilterDefinition : gatewayFilterDefinitions) {
                    FilterDefinition filterDefinition = new FilterDefinition();
                    filterDefinition.setName(gatewayFilterDefinition.getName());
                    filterDefinition.setArgs(gatewayFilterDefinition.getArgs());
                    filterDefinitions.add(filterDefinition);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return filterDefinitions;
    }

}
