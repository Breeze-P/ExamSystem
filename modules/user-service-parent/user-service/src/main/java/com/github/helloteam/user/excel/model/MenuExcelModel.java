package com.github.helloteam.user.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.github.helloteam.common.basic.utils.excel.annotation.ExcelModel;
import lombok.Data;

/**
 * 菜单Excel Model
 *
 * @author zdz
 * @date 2022/04/16 11:28
 */
@Data
@ExcelModel("菜单信息")
@ContentRowHeight(18)
@HeadRowHeight(20)
@ColumnWidth(25)
public class MenuExcelModel {

	/**
	 * 菜单ID
	 */
	@ExcelProperty(value = "菜单id", converter = LongStringConverter.class)
	private Long id;

	/**
	 * 菜单名称
	 */
	@ExcelProperty("菜单名称")
	private String name;

	/**
	 * 菜单权限标识
	 */
	@ExcelProperty("权限标识")
	private String permission;

	/**
	 * 菜单URL
	 */
	@ExcelProperty("url")
	private String url;

	/**
	 * 菜单重定向的URL
	 */
	@ExcelProperty("重定向url")
	private String redirect;

	/**
	 * 父菜单ID
	 */
	@ExcelProperty(value = "父菜单ID", converter = LongStringConverter.class)
	private Long parentId;

	/**
	 * 菜单图标
	 */
	@ExcelProperty("图标")
	private String icon;

	/**
	 * 菜单排序号
	 */
	@ExcelProperty("排序号")
	private String sort;

	/**
	 * 菜单类型
	 */
	@ExcelProperty("类型")
	private String type;

	/**
	 * 菜单所属模块
	 */
	@ExcelProperty("模块")
	private String component;

	/**
	 * 菜单所在路径
	 */
	@ExcelProperty("路径")
	private String path;

	/**
	 * 菜单备注
	 */
	@ExcelProperty("备注")
	private String remark;

	/**
	 * 菜单系统编码
	 */
	@ExcelProperty("系统编码")
	private String applicationCode;

	/**
	 * 租户标识
	 */
	@ExcelProperty("租户标识")
	private String tenantCode;

}
