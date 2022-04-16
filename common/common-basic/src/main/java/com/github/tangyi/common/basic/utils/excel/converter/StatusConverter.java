package com.github.tangyi.common.basic.utils.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.github.tangyi.common.basic.enums.StatusEnum;

/**
 * 状态转换器（excel和java之间的转换）
 *
 * @author zdz
 * @date 2022/04/16 11:23
 */
public class StatusConverter implements Converter<Integer> {

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
        return StatusEnum.matchByName(cellData.getStringValue()).getValue();
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
        return new CellData<>(StatusEnum.matchByValue(value).getName());
    }

}
