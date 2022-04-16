package com.github.helloteam.exam.mapper;

import com.github.helloteam.common.core.persistence.CrudMapper;
import com.github.helloteam.exam.api.module.Pictures;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片mapper
 *
 * @author zdz
 * @date 2022/04/16 14:34
 */
@Mapper
public interface PicturesMapper extends CrudMapper<Pictures> {
}
