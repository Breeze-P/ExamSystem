package com.github.tangyi.common.core.utils;

import com.github.tangyi.common.core.persistence.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装常见的树实体操作的utils类
 *
 * @author zdz
 * @date 2022/04/10 15:24
 */
public class TreeUtil {

    /**
     * 两层循环实现建树
     *
     * @param treeEntities 传入的树实体列表
     * @return List
     */
    @SuppressWarnings(value = "unchecked")
    public static <T> List<T> buildTree(List<? extends TreeEntity<T>> treeEntities, Object root) {
        List<TreeEntity<T>> treeEntityArrayList = new ArrayList<>();
        treeEntities.forEach(treeEntity -> {
            if (treeEntity.getParentId().equals(root))
                treeEntityArrayList.add(treeEntity);
            treeEntities.forEach(childTreeEntity -> {
                if (childTreeEntity.getParentId().equals(treeEntity.getId())) {
                    if (treeEntity.getChildren() == null)
                        treeEntity.setChildren(new ArrayList<>());
                    treeEntity.add(childTreeEntity);
                }
            });
        });
        return (List<T>) treeEntityArrayList;
    }

}
