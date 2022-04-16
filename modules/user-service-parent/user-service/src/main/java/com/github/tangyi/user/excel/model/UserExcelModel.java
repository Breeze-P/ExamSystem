package com.github.tangyi.user.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.github.tangyi.common.basic.utils.excel.annotation.ExcelModel;
import com.github.tangyi.common.basic.utils.excel.converter.GenderConverter;
import com.github.tangyi.common.basic.utils.excel.converter.StatusConverter;
import com.github.tangyi.user.excel.IdentityTypeConverter;
import lombok.Data;

import java.util.Date;

/**
 * 关于用户信息的ExcelModel
 *
 * @author zdz
 * @date 2022/04/16 11:28
 */
@Data
@ExcelModel("用户信息")
@ContentRowHeight(18)
@HeadRowHeight(20)
@ColumnWidth(15)
public class UserExcelModel {

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户id", converter = LongStringConverter.class)
    @ColumnWidth(20)
    private Long id;

    /**
     * 用户账号
     */
    @ExcelProperty("账号")
    private String identifier;

    /**
     * 用户账号类型
     */
    @ExcelProperty(value = "账号类型", converter = IdentityTypeConverter.class)
    private Integer identityType;

    /**
     * 用户姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 用户性别
     */
    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    private Integer sex;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    @ColumnWidth(20)
    private String email;

    /**
     * 生日
     */
    @ExcelProperty("生日")
    @DateTimeFormat("yyyy年MM月dd日")
    private Date born;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = StatusConverter.class)
    private Integer status;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID", converter = LongStringConverter.class)
    @ColumnWidth(20)
    private Long deptId;

    /**
     * 系统编码
     */
    @ExcelProperty("系统编码")
    private String applicationCode;

    /**
     * 租户标识
     */
    @ExcelProperty("租户标识")
    private String tenantCode;

}
