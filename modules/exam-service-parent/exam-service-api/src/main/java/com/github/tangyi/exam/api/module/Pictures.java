package com.github.tangyi.exam.api.module;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 图片表
 *
 * @author zdz
 * @date 2022/04/16 14:13
 */
@Data
public class Pictures extends BaseEntity<Pictures> {

    /**
     * 图片地址
     */
    private String pictureAddress;

    /**
     * 附件ID
     */
    private String attachmentId;

}
