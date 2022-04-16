package com.github.helloteam.exam.excel.listener;

import com.github.helloteam.common.basic.utils.excel.AbstractExcelImportListener;
import com.github.helloteam.common.security.utils.SysUtil;
import com.github.helloteam.exam.api.dto.SubjectDto;
import com.github.helloteam.exam.api.module.Answer;
import com.github.helloteam.exam.api.module.SubjectOption;
import com.github.helloteam.exam.excel.model.SubjectExcelModel;
import com.github.helloteam.exam.service.SubjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目导入
 *
 * @author zdz
 * @date 2022/04/16 14:30
 */
public class SubjectImportListener extends AbstractExcelImportListener<SubjectExcelModel> {

    /**
     * 题目service
     */
    private SubjectService subjectService;

    /**
     * 考试ID
     */
    private Long examinationId;

    /**
     * 题目类型ID
     */
    private Long categoryId;

    /**
     * 构造器
     */
    public SubjectImportListener(SubjectService subjectService, Long examinationId, Long categoryId) {
        this.subjectService = subjectService;
        this.examinationId = examinationId;
        this.categoryId = categoryId;
    }

    /**
     * 保存数据
     *
     * @param subjectExcelModelList 题目ExcelModel列表
     */
    @Override
    public void saveData(List<SubjectExcelModel> subjectExcelModelList) {
        logger.info("SaveData size: {}", subjectExcelModelList.size());
        List<SubjectDto> subjects = new ArrayList<>();
        String creator = SysUtil.getUser();
        String sysCode = SysUtil.getSysCode();
        String tenantCode = SysUtil.getTenantCode();
        subjectExcelModelList.forEach(subject -> {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setCommonValue(creator, sysCode, tenantCode);
            BeanUtils.copyProperties(subject, subjectDto);
            List<SubjectOption> subjectOptions = new ArrayList<>();
            if (StringUtils.isNotBlank(subject.getOptions())) {
                String[] options = subject.getOptions().split("\\$\\$");
                // $$A# 测试测试
                for (String option : options) {
                    if (StringUtils.isNotBlank(option)) {
                        String[] optionInfos = option.split("#");
                        if (optionInfos.length >= 2) {
                            // 去掉$$
                            String optionName = optionInfos[0].trim();
                            StringBuilder optionContent = new StringBuilder();
                            if (optionInfos.length > 2) {
                                for (int i = 1; i < optionInfos.length; i++) {
                                    optionContent.append(optionInfos[i].trim());
                                }
                            } else {
                                optionContent = new StringBuilder(optionInfos[1].trim());
                            }
                            SubjectOption subjectOption = new SubjectOption();
                            subjectOption.setOptionName(optionName);
                            subjectOption.setOptionContent(optionContent.toString());
                            subjectOptions.add(subjectOption);
                        }
                    }
                }
            }
            subjectDto.setOptions(subjectOptions);
            // 答案
            Answer answer = new Answer();
            answer.setAnswer(subject.getAnswer());
            subjectDto.setAnswer(answer);
            subjects.add(subjectDto);
        });
        subjectService.importSubject(subjects, examinationId, categoryId);
    }

}
