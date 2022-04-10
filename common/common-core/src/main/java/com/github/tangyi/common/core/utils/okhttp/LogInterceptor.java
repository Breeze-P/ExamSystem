package com.github.tangyi.common.core.utils.okhttp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 日志拦截器类
 *
 * @author zdz
 * @date 2022/04/10 15:31
 */
public class LogInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    /**
     * 具体拦截操作逻辑
     *
     * @param chain Chain
     * @return 拦截结果反馈
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        long t1 = System.nanoTime();
        Request request = chain.request();
        logger.debug(String.format("sending %s request %s%n%s", request.method(),
                request.url(), request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        logger.debug(String.format("received response for %s in %.1fms%n%s", response.request().url(),
                (t2 - t1) / 1e6d, response.headers()));
        return response;
    }

}
