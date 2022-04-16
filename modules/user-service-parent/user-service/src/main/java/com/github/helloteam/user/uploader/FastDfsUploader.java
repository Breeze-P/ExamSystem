package com.github.helloteam.user.uploader;

import com.github.helloteam.oss.service.FastDfsService;
import com.github.helloteam.user.api.module.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 上传到FastDfs
 *
 * @author zdz
 * @date 2022/04/16 11:53
 */
@Slf4j
@Service
public class FastDfsUploader extends AbstractUploader {

    /**
     * FastDfs service类
     */
    @Autowired
    private FastDfsService fastDfsService;

    /**
     * 上传附件
     *
     * @param attachment 需要上传的附件信息
     * @param bytes      buffer
     * @return 附件信息
     */
    @Override
    public Attachment upload(Attachment attachment, byte[] bytes) {
        try {
            attachment.setAttachSize(String.valueOf(bytes.length));
            String fastFileId = fastDfsService.uploadFile(
                    new ByteArrayInputStream(bytes), bytes.length, attachment.getAttachType());
            String groupName = fastFileId.substring(0, fastFileId.indexOf("/"));
            attachment.setFastFileId(fastFileId);
            attachment.setGroupName(groupName);
            return attachment;
        } catch (Exception e) {
            log.error("上传附件至网盘失败:" + attachment.getAttachName() + e.getMessage());
            return null;
        }
    }

    /**
     * 下载附件
     *
     * @param attachment 需要下载的附件信息
     * @return 负责下载的输入流
     */
    @Override
    public InputStream download(Attachment attachment) {
        return fastDfsService.downloadStream(attachment.getGroupName(), attachment.getFastFileId());
    }

    /**
     * 删除附件
     *
     * @param attachment 需要删除的附件信息
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Attachment attachment) {
        if (StringUtils.isNotEmpty(attachment.getGroupName()) && StringUtils.isNotEmpty(attachment.getFastFileId())) {
            fastDfsService.deleteFile(attachment.getGroupName(), attachment.getFastFileId());
        }
        return Boolean.TRUE;
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
