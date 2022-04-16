package com.github.helloteam.exam.service;

import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.exam.api.module.Knowledge;
import com.github.helloteam.exam.mapper.KnowledgeMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 知识库service
 *
 * @author zdz
 * @date 2022/04/16 14:39
 */
@Service
public class KnowledgeService extends CrudService<KnowledgeMapper, Knowledge> {

    /**
     * 获取知识库信息
     *
     * @param knowledge knowledge
     * @return Knowledge
     */
    @Override
    @Cacheable(value = "knowledge#" + CommonConstant.CACHE_EXPIRE, key = "#knowledge.id")
    public Knowledge get(Knowledge knowledge) {
        return super.get(knowledge);
    }

    /**
     * 更新知识库信息
     *
     * @param knowledge knowledge
     * @return int
     */
    @Override
    @Transactional
    @CacheEvict(value = "knowledge", key = "#knowledge.id")
    public int update(Knowledge knowledge) {
        return super.update(knowledge);
    }

    /**
     * 删除知识库信息
     *
     * @param knowledge knowledge
     * @return int
     */
    @Override
    @Transactional
    @CacheEvict(value = "knowledge", key = "#knowledge.id")
    public int delete(Knowledge knowledge) {
        return super.delete(knowledge);
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return int
     */
    @Override
    @Transactional
    @CacheEvict(value = "knowledge", allEntries = true)
    public int deleteAll(Long[] ids) {
        return super.deleteAll(ids);
    }

}
