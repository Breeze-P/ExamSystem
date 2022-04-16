package com.github.helloteam.exam.api.feign.factory;

import com.github.helloteam.exam.api.feign.ExaminationServiceClient;
import com.github.helloteam.exam.api.feign.fallback.ExaminationServiceClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zdz
 * @date 2022/04/16 14:21
 */
@Component
public class ExaminationServiceClientFallbackFactory implements FallbackFactory<ExaminationServiceClient> {

    @Override
    public ExaminationServiceClient create(Throwable throwable) {
        ExaminationServiceClientFallbackImpl examinationServiceClientFallback = new ExaminationServiceClientFallbackImpl();
        examinationServiceClientFallback.setThrowable(throwable);
        return examinationServiceClientFallback;
    }

}