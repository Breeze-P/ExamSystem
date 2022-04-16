package com.github.helloteam.user.service;

import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.service.CrudService;
import com.github.helloteam.user.api.constant.AttachmentConstant;
import com.github.helloteam.user.api.module.Attachment;
import com.github.helloteam.user.mapper.AttachmentMapper;
import com.github.helloteam.user.uploader.UploadInvoker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * 附件service
 *
 * @author zdz
 * @date 2022/04/16 12:09
 */
@Slf4j
@AllArgsConstructor
@Service
public class AttachmentService extends CrudService<AttachmentMapper, Attachment> {

	/**
	 * 根据id查询附件
	 *
	 * @param attachment 附件信息
	 * @return 附件信息
	 */
	@Cacheable(value = "attachment#" + CommonConstant.CACHE_EXPIRE, key = "#attachment.id")
	@Override
	public Attachment get(Attachment attachment) {
		return super.get(attachment);
	}

	/**
	 * 根据id更新
	 *
	 * @param attachment 附件信息
	 * @return 是否更新成功
	 */
	@Override
	@Transactional
	@CacheEvict(value = {"attachment", "attachment_preview"}, key = "#attachment.id")
	public int update(Attachment attachment) {
		return super.update(attachment);
	}

	/**
	 * 下载附件
	 *
	 * @param attachment 附件信息
	 * @return 负责下载的输入流
	 */
	public InputStream download(Attachment attachment) throws Exception {
		return UploadInvoker.getInstance().download(attachment);
	}

	/**
	 * 删除
	 *
	 * @param attachment 附件信息
	 * @return 是否删除成功
	 */
	@Override
	@Transactional
	@CacheEvict(value = {"attachment", "attachment_preview"}, key = "#attachment.id")
	public int delete(Attachment attachment) {
		return super.delete(attachment);
	}

	/**
	 * 批量删除
	 *
	 * @param ids ids
	 * @return 是否删除成功
	 */
	@Override
	@Transactional
	@CacheEvict(value = {"attachment", "attachment_preview"}, allEntries = true)
	public int deleteAll(Long[] ids) {
		return super.deleteAll(ids);
	}

	/**
	 * 获取附件的预览地址
	 *
	 * @param attachment 附件信息
	 * @return 预览地址
	 */
	@Cacheable(value = "attachment_preview#" + CommonConstant.CACHE_EXPIRE, key = "#attachment.id")
	public String getPreviewUrl(Attachment attachment) {
		attachment = this.get(attachment);
		if (attachment != null) {
			// 处理URL地址
			String preview = attachment.getPreviewUrl();
			if (StringUtils.isNotBlank(preview) && !preview.startsWith("http")) {
				preview = "http://" + preview;
			} else {
				preview = AttachmentConstant.ATTACHMENT_PREVIEW_URL + attachment.getId();
			}
			log.debug("GetPreviewUrl id: {}, preview url: {}", attachment.getId(), preview);
			return preview;
		}
		return "";
	}

}
