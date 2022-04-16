package com.github.helloteam.exam.mq;

import com.github.helloteam.common.core.constant.MqConstant;
import com.github.helloteam.common.security.tenant.TenantContextHolder;
import com.github.helloteam.exam.api.enums.SubmitStatusEnum;
import com.github.helloteam.exam.api.module.Answer;
import com.github.helloteam.exam.api.module.ExaminationRecord;
import com.github.helloteam.exam.service.AnswerService;
import com.github.helloteam.exam.service.ExamRecordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 提交考试消息消费者
 *
 * @author zdz
 * @date 2022/04/16 14:43
 */
@Slf4j
@AllArgsConstructor
@Service
public class RabbitSubmitExaminationReceiver {

    private final AnswerService answerService;

    private final ExamRecordService examRecordService;

    /**
     * 处理提交考试逻辑：统计错题，计算成绩等
     * 1. 先更新考试记录的状态为正在计算
     * 2. 更新成功则执行提交逻辑
     *
     * @param answer answer
     */
    @RabbitListener(queues = {MqConstant.SUBMIT_EXAMINATION_QUEUE})
    public void submitExamination(Answer answer) {
        log.debug("examRecordId: {}, modifier: {}", answer.getExamRecordId(), answer.getModifier());
        try {
            // 异步提交会丢失tenantCode，需要手动设置
            TenantContextHolder.setTenantCode(answer.getTenantCode());
            ExaminationRecord examRecord = examRecordService.get(answer.getExamRecordId());
            if (examRecord == null)
                return;
            if (SubmitStatusEnum.NOT_SUBMITTED.getValue().equals(examRecord.getSubmitStatus()))
                log.warn("Examination: {} not submitted", examRecord.getId());
            if (SubmitStatusEnum.CALCULATE.getValue().equals(examRecord.getSubmitStatus()))
                log.warn("Examination: {} is counting, please do not submit again", examRecord.getId());
            // 更新状态为正在统计
            examRecord.setSubmitStatus(SubmitStatusEnum.CALCULATE.getValue());
            // 更新成功
            if (examRecordService.update(examRecord) > 0) {
                log.debug("Examination: {} count success", examRecord.getId());
                answerService.submit(answer);
            } else {
                log.warn("Examination: {} count failed", examRecord.getId());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
