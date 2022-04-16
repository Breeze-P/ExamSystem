package com.github.helloteam.user.uploader;

import com.github.helloteam.user.api.module.Attachment;

import java.io.InputStream;

/**
 * 附件上传器接口类
 *
 * @author zdz
 * @date 2022/04/16 11:37
 */
public interface IUploader {

    /**
     * 上传附件
     *
     * @param attachment 需要上传的附件信息
     * @param bytes      buffer
     * @return 附件信息
     */
    Attachment upload(Attachment attachment, byte[] bytes);

    /**
     * 保存附件信息
     *
     * @param attachment 需要保存的附件信息
     * @return 是否保存成功
     */
    int save(Attachment attachment);

    /**
     * 下载附件
     *
     * @param attachment 需要下载的附件信息
     * @return 负责下载附件的输入流
     */
    InputStream download(Attachment attachment);

    /**
     * 删除附件
     *
     * @param attachment 需要删除的附件信息
     * @return 是否删除成功
     */
    boolean delete(Attachment attachment);

    /**
     * 批量删除
     *
     * @param attachment 需要删除的附件信息
     * @return 是否删除成功
     */
    boolean deleteAll(Attachment attachment);

}
