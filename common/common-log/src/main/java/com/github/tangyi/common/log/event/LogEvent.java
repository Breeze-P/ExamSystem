package com.github.tangyi.common.log.event;

import com.github.tangyi.common.basic.model.Log;
import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author zdz
 * @date 2022/04/11 15:54
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(Log source) {
        super(source);
    }

}
