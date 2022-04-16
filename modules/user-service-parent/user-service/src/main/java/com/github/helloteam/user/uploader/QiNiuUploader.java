package com.github.helloteam.user.uploader;

import com.github.helloteam.oss.service.QiNiuUtil;
import com.github.helloteam.user.api.module.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 上传到七牛云
 *
 * @author zdz
 * @date 2022/04/16 11:57
 */
@Slf4j
@Service
public class QiNiuUploader extends AbstractUploader {

    /**
     * 上传附件
     *
     * @param attachment 需要上传的附件信息
     * @param bytes      buffer
     * @return 附件信息
     */
    @Override
    public Attachment upload(Attachment attachment, byte[] bytes) {
        String result = QiNiuUtil.getInstance().upload(bytes, attachment.getAttachName());
        attachment.setUploadResult(result);
        attachment.setPreviewUrl(attachment.getUploadResult());
        return attachment;
    }

    /**
     * 下载附件
     *
     * @param attachment 需要下载的附件信息
     * @return 负责下载的输入流
     */
    @Override
    public InputStream download(Attachment attachment) {
        return null;
    }

    /**
     * 删除附件
     *
     * @param attachment 需要删除的附件信息
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Attachment attachment) {
        return QiNiuUtil.getInstance().delete(attachment.getAttachName());
    }

    /**
     * 批量删除
     *
     * @param attachment 需要删除的附件信息
     * @return 是否删除成功
     */
    @Override
    public boolean deleteAll(Attachment attachment) {
        return false;
    }

}
