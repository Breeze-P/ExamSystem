package com.github.helloteam.exam.handler;

import com.github.helloteam.common.core.utils.SpringContextHolder;
import com.github.helloteam.exam.api.dto.SubjectDto;
import com.github.helloteam.exam.api.module.Answer;
import com.github.helloteam.exam.service.SubjectService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计成绩
 *
 * @author zdz
 * @date 2022/04/16 14:47
 */
public abstract class AbstractAnswerHandler implements IAnswerHandler {

    /**
     * 处理逻辑
     *
     * @param answers 答案列表
     * @return 答案处理结果
     */
    @Override
    public AnswerHandleResult handle(List<Answer> answers) {
        if (CollectionUtils.isNotEmpty(answers)) {
            // 保存答题正确的题目分数
            List<Double> rightScore = new ArrayList<>();
            // 获取题目信息
            List<SubjectDto> subjects = getSubjects(answers);
            answers.forEach(tempAnswer -> {
                subjects.stream()
                        // 题目ID匹配
                        .filter(tempSubject -> tempSubject.getId().equals(tempAnswer.getSubjectId())).findFirst()
                        .ifPresent(subject -> judge(tempAnswer, subject, rightScore));
            });
            AnswerHandleResult result = new AnswerHandleResult();
            // 记录总分、正确题目数、错误题目数
            result.setScore(rightScore.stream().mapToDouble(Double::valueOf).sum());
            result.setCorrectNum(rightScore.size());
            result.setInCorrectNum(answers.size() - rightScore.size());
            return result;
        }
        return null;
    }

    /**
     * 获取题目
     *
     * @param answers 答案
     * @return 题目列表
     */
    @Override
    public List<SubjectDto> getSubjects(List<Answer> answers) {
        return SpringContextHolder.getApplicationContext().getBean(SubjectService.class)
                .findListById(getSubjectType().getValue(),
                        answers.stream().map(Answer::getSubjectId).distinct().toArray(Long[]::new));
    }

}
