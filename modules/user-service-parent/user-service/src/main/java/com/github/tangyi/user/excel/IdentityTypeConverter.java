package com.github.tangyi.user.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.github.tangyi.user.api.enums.IdentityType;

/**
 * 用户授权类型转换器（excel和java之间的转换）
 * （用户密码、手机号、微信、QQ等）
 *
 * @author zdz
 * @date 2022/04/16 11:31
 */
public class IdentityTypeConverter implements Converter<Integer> {

    /**
     * Java数据类型支持
     *
     * @return Java数据类型
     */
    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    /**
     * Excel数据类型支持
     *
     * @return Excel数据类型
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 将Excel数据类型转化为对应的Java数据类型
     *
     * @param cellData            Excel数据类型
     * @param contentProperty     Excel内容属性
     * @param globalConfiguration 全局配置
     * @return 对应的Java数据类型
     */
    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) throws Exception {
        return IdentityType.matchByDesc(cellData.getStringValue()).getValue();
    }

    /**
     * 将Java数据类型转化为对应的Excel数据类型
     *
     * @param value               Java数据类型
     * @param contentProperty     Excel内容属性
     * @param globalConfiguration 全局配置
     * @return 对应的Excel数据类型
     */
    @Override
    public CellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData<>(IdentityType.matchByType(value).getDesc());
    }

}
