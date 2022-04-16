package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.Attachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件mapper
 *
 * @author zdz
 * @date 2022/04/16 11:58
 */
@Mapper
public interface AttachmentMapper extends CrudMapper<Attachment> {

}
