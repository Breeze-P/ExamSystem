package com.github.helloteam.user.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.helloteam.common.core.persistence.TreeEntity;
import com.github.helloteam.user.api.module.Menu;
import lombok.Data;

/**
 * 菜单数据传输对象类
 *
 * @author zdz
 * @date 2022/04/15 00:15
 */
@Data
public class MenuDto extends TreeEntity<MenuDto> {

    /**
     * 父菜单ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 名称
     */
    private String name;

    /**
     * URL
     */
    private String url;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * spread
     */
    private boolean spread = false;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     *
     */
    private String authority;

    /**
     * code
     */
    private String code;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 标签
     */
    private String label;

    /**
     * 角色
     */
    private String[] roles;

    /**
     * remark
     */
    private String remark;

    /**
     * 构造器
     * @param menu 菜单实例
     */
    public MenuDto(Menu menu) {
        this.id = menu.getId();
        this.parentId = menu.getParentId();
        this.icon = menu.getIcon();
        this.name = menu.getName();
        this.url = menu.getUrl();
        this.type = menu.getType();
        this.label = menu.getName();
        this.sort = Integer.parseInt(menu.getSort());
        this.component = menu.getComponent();
        this.path = menu.getPath();
        this.redirect = menu.getRedirect();
        this.remark = menu.getRemark();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

}
