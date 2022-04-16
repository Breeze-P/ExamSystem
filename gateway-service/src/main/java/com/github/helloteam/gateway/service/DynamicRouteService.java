package com.github.helloteam.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 动态路由Service
 *
 * @author zdz
 * @date 2022/04/11 21:37
 */
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {

    private final RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public DynamicRouteService(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    /**
     * 增加路由
     *
     * @param definition 路由信息
     * @return 添加路由操作的结果信息
     */
    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /**
     * 更新路由
     *
     * @param definition 路由信息
     * @return 更新路由操作的结果信息
     */
    public String update(RouteDefinition definition) {
        // 删除路由
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail, did not find route with routeId: " + definition.getId();
        }
        // 增加路由
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            return "success";
        } catch (Exception e) {
            return "update route fail";
        }
    }

    /**
     * 删除路由
     *
     * @param id
     * @return Mono
     */
    public Mono<ResponseEntity<Object>> delete(Long id) {
        return this.routeDefinitionWriter.delete(Mono.just(String.valueOf(id)))
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
