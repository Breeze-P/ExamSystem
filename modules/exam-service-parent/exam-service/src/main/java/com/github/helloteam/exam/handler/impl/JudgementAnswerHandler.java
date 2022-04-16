package com.github.helloteam.exam.handler.impl;

import com.github.helloteam.exam.api.constants.AnswerConstant;
import com.github.helloteam.exam.api.dto.SubjectDto;
import com.github.helloteam.exam.api.module.Answer;
import com.github.helloteam.exam.enums.SubjectTypeEnum;
import com.github.helloteam.exam.handler.AbstractAnswerHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 判断题
 *
 * @author zdz
 * @date 2022/04/16 14:52
 */
@Slf4j
@AllArgsConstructor
@Component
public class JudgementAnswerHandler extends AbstractAnswerHandler {

    @Override
    public SubjectTypeEnum getSubjectType() {
        return SubjectTypeEnum.JUDGEMENT;
    }

    @Override
    public boolean judgeRight(Answer answer, SubjectDto subject) {
        return subject.getAnswer().getAnswer().equalsIgnoreCase(answer.getAnswer());
    }

    /**
     * 判断正误
     *
     * @param answer     answer
     * @param subject    subject
     * @param rightScore rightScore
     */
    @Override
    public void judge(Answer answer, SubjectDto subject, List<Double> rightScore) {
        if (judgeRight(answer, subject)) {
            rightScore.add(subject.getScore());
            answer.setAnswerType(AnswerConstant.RIGHT);
            answer.setScore(subject.getScore());
        } else {
            answer.setAnswerType(AnswerConstant.WRONG);
            answer.setScore(0.0);
        }
        answer.setMarkStatus(AnswerConstant.MARKED);
    }

}
