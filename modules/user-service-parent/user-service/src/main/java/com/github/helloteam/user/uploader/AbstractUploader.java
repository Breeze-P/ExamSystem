package com.github.helloteam.user.uploader;

import com.github.helloteam.common.basic.properties.SysProperties;
import com.github.helloteam.common.core.utils.SpringContextHolder;
import com.github.helloteam.user.api.module.Attachment;
import com.github.helloteam.user.service.AttachmentService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;

/**
 * 实现IUploader的附件上传器抽象类
 * 通过调用AttachmentService实现相关功能
 *
 * @author zdz
 * @date 2022/04/16 11:40
 */
public abstract class AbstractUploader implements IUploader {

    /**
     * 上传附件
     *
     * @param attachment 需要上传的附件信息
     * @param bytes      buffer
     * @return 附件信息
     */
    @Override
    public abstract Attachment upload(Attachment attachment, byte[] bytes);

    /**
     * 抽象方法，下载附件
     *
     * @param attachment 需要下载的附件信息
     * @return 负责下载附件的输入流
     */
    @Override
    public abstract InputStream download(Attachment attachment);

    /**
     * 保存附件
     *
     * @param attachment 需要保存的附件信息
     * @return 是否保存成功
     */
    @Override
    public int save(Attachment attachment) {
        return SpringContextHolder.getApplicationContext().getBean(AttachmentService.class).insert(attachment);
    }

    /**
     * 删除福建
     *
     * @param attachment 需要删除的附件信息
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Attachment attachment) {
        return SpringContextHolder.getApplicationContext().getBean(AttachmentService.class).delete(attachment) > 0;
    }

    /**
     * 获取附件存储目录
     *
     * @param attachment 附件信息
     * @param id         id
     * @return 存储附件的目录路径
     */
    public String getFileRealDirectory(Attachment attachment, String id) {
        // 获取附件的相关信息
        String applicationCode = attachment.getApplicationCode();
        String busiId = attachment.getBusiId();
        String fileName = attachment.getAttachName();
        String fileRealDirectory = SpringContextHolder.getApplicationContext()
                .getBean(SysProperties.class).getAttachPath() + File.separator
                + applicationCode + File.separator;
        // 若有分类则加上
        if (StringUtils.isNotBlank(attachment.getBusiModule())) {
            // 业务模块
            String busiModule = attachment.getBusiModule();
            fileRealDirectory = fileRealDirectory + busiModule + File.separator;
        }
        if (StringUtils.isNotBlank(attachment.getBusiType())) {
            // 业务类型
            String busiType = attachment.getBusiType();
            fileRealDirectory = fileRealDirectory + busiType + File.separator;
        }
        fileRealDirectory = fileRealDirectory + busiId;
        return fileRealDirectory;
    }

}
