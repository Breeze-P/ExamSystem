package com.github.helloteam.user.api.constant;

/**
 * 封装附件相关的常量
 *
 * @author zdz
 * @date 2022/04/15 00:02
 */
public class AttachmentConstant {

    /**
     * 普通附件
     */
    public static final String BUSI_TYPE_NORMAL_ATTACHMENT = "0";

    /**
     * 用户头像
     */
    public static final String BUSI_TYPE_USER_AVATAR = "1";

    /**
     * 知识库附件
     */
    public static final String BUSI_TYPE_KNOWLEDGE_ATTACHMENT = "2";

    /**
     * 附件预览地址
     */
    public static final String ATTACHMENT_PREVIEW_URL = "/api/user/v1/attachment/preview?id=";

}
