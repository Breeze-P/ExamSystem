package com.github.helloteam.exam.service;

import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.exam.api.module.ExaminationAuth;
import com.github.helloteam.exam.mapper.ExaminationAuthMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考试权限Service
 *
 * @author zdz
 * @date 2022/04/16 14:37
 */
@Slf4j
@AllArgsConstructor
@Service
public class ExaminationAuthService extends CrudService<ExaminationAuthMapper, ExaminationAuth> {

    /**
     * 根据考生ID查询
     *
     * @param examinationAuth examinationAuth
     * @return List<ExaminationAuth>
     */
    public List<ExaminationAuth> finListByUserId(ExaminationAuth examinationAuth) {
        return null;
    }

    /**
     * 根据考试ID查询
     *
     * @param examinationAuth examinationAuth
     * @return List<ExaminationAuth>ß
     */
    public List<ExaminationAuth> finListByExaminationId(ExaminationAuth examinationAuth) {
        return null;
    }

    /**
     * 根据考试ID删除
     *
     * @param examinationAuth examinationAuth
     * @return int
     */
    @Transactional
    public int deleteByExaminationId(ExaminationAuth examinationAuth) {
        return -1;
    }

    /**
     * 根据考生ID删除
     *
     * @param examinationAuth examinationAuth
     * @return int
     */
    @Transactional
    public int deleteByUserId(ExaminationAuth examinationAuth) {
        return -1;
    }

}

