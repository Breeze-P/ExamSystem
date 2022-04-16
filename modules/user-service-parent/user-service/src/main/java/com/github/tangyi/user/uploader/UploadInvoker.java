package com.github.tangyi.user.uploader;

import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.user.api.module.Attachment;
import com.github.tangyi.user.enums.AttachUploaderEnum;
import com.github.tangyi.user.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 上传操作invoker
 *
 * @author zdz
 * @date 2022/04/16 11:44
 */
@Slf4j
public class UploadInvoker {

    /**
     * uploadInvoker单例实例
     */
    private static UploadInvoker instance;

    /**
     * 上传器map
     */
    private Map<Integer, IUploader> uploaderMap = null;

    /**
     * 附件service类
     */
    private AttachmentService attachmentService;

    /**
     * 构造器
     * @param attachmentService 附件service类
     */
    public UploadInvoker(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    /**
     * 单例，获取uploadInvoker单例实例
     *
     * @return uploadInvoker单例实例
     */
    public synchronized static UploadInvoker getInstance() {
        if (instance == null) {
            instance = new UploadInvoker(SpringContextHolder.getApplicationContext().getBean(AttachmentService.class));
        }
        return instance;
    }

    /**
     * 上传附件
     *
     * @param attachment 需要上传的附件信息
     * @param bytes      buffer
     * @return 附件信息
     */
    public Attachment upload(Attachment attachment, byte[] bytes) {
        if (attachment == null || bytes == null) {
            return null;
        }
        // 设置附件的上传类型
        if (attachment.getUploadType() == null) {
            String uploadType = SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachUploadType();
            if (StringUtils.isNotBlank(uploadType)) {
                attachment.setUploadType(Integer.parseInt(uploadType));
            }
        }
        IUploader uploader = this.getUploader(attachment.getUploadType());
        // 若没有对应的上传器，则抛出异常
        if (uploader == null) {
            throw new CommonException("uploader is null");
        }
        // 上传附件
        attachment = uploader.upload(attachment, bytes);
        if (attachment != null) {
            uploader.save(attachment);
        }
        return attachment;
    }

    /**
     * 下载附件
     *
     * @param attachment attachment
     * @return Attachment
     */
    public InputStream download(Attachment attachment) {
        if (attachment == null)
            return null;
        IUploader uploader = this.getUploader(attachment.getUploadType());
        if (uploader == null)
            throw new CommonException("uploader is null");
        return uploader.download(attachment);
    }

    /**
     * 删除附件
     *
     * @param attachment attachment
     * @return Attachment
     */
    public boolean delete(Attachment attachment) {
        if (attachment == null)
            return Boolean.FALSE;
        IUploader uploader = this.getUploader(attachment.getUploadType());
        if (uploader == null)
            throw new CommonException("uploader is null");
        return uploader.delete(attachment);
    }

    /**
     * 批量删除附件
     *
     * @param ids ids
     * @return Attachment
     */
    public boolean deleteAll(Long[] ids) {
        boolean result = false;
        for (Long id : ids) {
            // 查询出实体
            Attachment attachmentSearch = new Attachment();
            attachmentSearch.setId(id);
            attachmentSearch = attachmentService.get(attachmentSearch);
            IUploader uploader = getUploader(attachmentSearch.getUploadType());
            // 删除对应存储方式中的附件
            result = uploader.delete(attachmentSearch);
            if (result) {
                uploader.delete(attachmentSearch);
            }
        }
        return result;
    }

    /**
     * 获取附件实现类
     *
     * @param uploadType uploadType
     * @return IUploader
     */
    private IUploader getUploader(Integer uploadType) {
        IUploader uploader;
        if (uploaderMap == null) {
            uploaderMap = new HashMap<>();
        }
        uploader = uploaderMap.get(uploadType);
        try {
            if (uploader == null) {
                // 如果没有初始化则创建
                String implClass = AttachUploaderEnum.matchByValue(uploadType).getImplClass();
                Class<?> clazz = Class.forName(implClass);
                uploader = (IUploader) clazz.newInstance();
                uploaderMap.put(uploadType, uploader);
            }
        } catch (Exception e) {
            log.error("getUploader error:{}", e.getMessage(), e);
            return null;
        }
        return uploader;
    }

}
