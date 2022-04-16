package com.github.helloteam.common.core.persistence;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形实体
 *
 * @author zdz
 * @date 2022/04/10 15:19
 */
@Data
public abstract class TreeEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 7265456426423066026L;

    /**
     * code
     */
    protected String code;

    /**
     * 父节点
     */
    protected T parent;

    /**
     * 父节点id
     */
    protected Long parentId;

    /**
     * 排序号
     */
    protected Integer sort;

    /**
     * 子节点集合
     */
    protected List<TreeEntity> children = new ArrayList<>();

    /**
     * 添加结点
     *
     * @param node 新结点
     */
    public void add(TreeEntity node) {
        children.add(node);
    }

}
