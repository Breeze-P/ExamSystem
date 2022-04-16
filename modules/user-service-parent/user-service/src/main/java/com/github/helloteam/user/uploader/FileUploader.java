package com.github.helloteam.user.uploader;

import com.github.helloteam.common.core.exceptions.CommonException;
import com.github.helloteam.common.core.utils.FileUtil;
import com.github.helloteam.user.api.module.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 上传到本地目录
 *
 * @author zdz
 * @date 2022/04/16 11:50
 */
@Slf4j
@Service
public class FileUploader extends AbstractUploader {

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
            String fileRealDirectory = getFileRealDirectory(attachment, attachment.getId().toString());
            fileRealDirectory = fileRealDirectory.replaceAll("\\\\", "/");
            String fileName = attachment.getAttachName();
            attachment.setAttachSize(String.valueOf(bytes.length));
            log.info("file read directory: {}", fileRealDirectory);
            FileUtil.createDirectory(fileRealDirectory);
            log.info("start write file: {}", fileName);
            saveFileFormByteArray(bytes, fileRealDirectory, fileName);
            log.info("write file finished: {}", fileName);
            attachment.setFastFileId(fileRealDirectory);
            return attachment;
        } catch (Exception e) {
            log.error("FileUploader error：{}, {}", attachment.getAttachName(), e.getMessage(), e);
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
        // 获取文件路径地址
        String path = attachment.getFastFileId() + File.separator + attachment.getAttachName();
        InputStream input = null;
        try {
            // 处理文件目录路径地址
            String fileRealDirectory = getFileRealDirectory(attachment, attachment.getId().toString());
            fileRealDirectory = fileRealDirectory.replaceAll("\\\\", "/");
            if (StringUtils.isNotBlank(fileRealDirectory) && !fileRealDirectory.equals(attachment.getFastFileId())) {
                throw new CommonException("attach path validate failure！attachPath："
                        + attachment.getFastFileId() + ", fileRealDirectory:" + fileRealDirectory);
            }
            // 生成输入流
            input = new FileInputStream(new File(path));
        } catch (Exception e) {
            log.error("download attachment failure: {}", e.getMessage(), e);
        }
        return input;
    }

    /**
     * 删除附件
     *
     * @param attachment 需要删除的附件信息
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Attachment attachment) {
        // 获取文件路径
        String path = attachment.getFastFileId() + File.separator + attachment.getAttachName();
        File file = new File(path);
        if (file.delete()) {
            // 删除文件所在目录
            FileUtil.deleteDirectory(attachment.getFastFileId());
            return super.delete(attachment);
        }
        return Boolean.FALSE;
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

    /**
     * @param b        buffer
     * @param path     文件路径
     * @param fileName 文件名
     */
    private void saveFileFormByteArray(byte[] b, String path, String fileName) throws IOException {
        BufferedOutputStream fs = new BufferedOutputStream(
                new FileOutputStream(path + "/" + fileName, true));
        fs.write(b);
        fs.flush();
        fs.close();
    }

}
