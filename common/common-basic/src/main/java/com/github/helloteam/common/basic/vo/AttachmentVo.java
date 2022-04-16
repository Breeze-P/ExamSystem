package com.github.helloteam.common.basic.vo;

import com.github.helloteam.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 附件 VO类
 *
 * @author zdz
 * @date 2022/04/10 14:54
 */
@Data
public class AttachmentVo extends BaseEntity<AttachmentVo> {

    /**
     * 附件名称
     */
    private String attachName;

    /**
     * 附件大小
     */
    private String attachSize;

    /**
     * 业务流水号
     */
    private String busiId;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 业务模块
     */
    private String busiModule;

}
