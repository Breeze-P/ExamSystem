package com.github.helloteam.common.basic.utils.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.helloteam.common.basic.utils.excel.annotation.ExcelModel;
import com.github.helloteam.common.basic.utils.excel.exception.ExcelException;
import com.github.helloteam.common.core.utils.DateUtils;
import com.github.helloteam.common.core.utils.Servlets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * excel导入导出工具类
 *
 * @author zdz
 * @date 2022/04/16 11:12
 */
@Slf4j
public class ExcelToolUtil {

    /**
     * 默认的sheet名称
     */
    private static final String DEFAULT_SHEET_NAME = "sheet1";

    /**
     * 私有构造方法，禁止实例化
     */
    private ExcelToolUtil() {
    }

    /**
     * 导出Excel
     *
     * @param request  请求
     * @param response 响应
     * @param dataList 数据列表
     * @param clazz    泛型类型
     */
    public static <T> void writeExcel(HttpServletRequest request, HttpServletResponse response,
                                      List<T> dataList, Class<T> clazz) {
        // 获取fileName和sheetName
        ExcelModel excelModel = clazz.getDeclaredAnnotation(ExcelModel.class);
        String fileName = DateUtils.localDateMillisToString(LocalDateTime.now());
        String sheetName = DEFAULT_SHEET_NAME;
        if (excelModel != null) {
            fileName = excelModel.value() + fileName;
            sheetName = excelModel.sheets()[0];
        }
        // 导出
        writeExcel(request, response, dataList, fileName, sheetName, clazz);
    }

    /**
     * 导出Excel
     *
     * @param request   请求
     * @param response  响应
     * @param dataList  数据列表
     * @param fileName  文件名
     * @param sheetName sheet名
     * @param clazz     泛型类型
     */
    public static <T> void writeExcel(HttpServletRequest request, HttpServletResponse response,
                                      List<T> dataList, String fileName, String sheetName, Class<T> clazz) {
        ExcelWriter excelWriter = null;
        try {
            // 通过EasyExcel工厂，实例化excelWriter
            excelWriter = EasyExcelFactory
                    .write(getOutputStream(fileName, request, response, ExcelTypeEnum.XLSX), clazz)
                    .build();
            // 实例化将要写入的excel sheet
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(sheetName).build();
            excelWriter.write(dataList, writeSheet);
        } finally {
            // 记得关闭excelWriter
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 获取OutputStream
     *
     * @param fileName      文件名
     * @param request       请求
     * @param response      响应
     * @param excelTypeEnum excel输出文件类型
     * @return 输出流
     */
    private static OutputStream getOutputStream(String fileName, HttpServletRequest request,
                                                HttpServletResponse response, ExcelTypeEnum excelTypeEnum) {
        try {
            // 设置响应头，处理浏览器间的中文乱码问题
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
                    Servlets.getDownName(request, fileName + excelTypeEnum.getValue()));
            return response.getOutputStream();
        } catch (IOException e) {
            throw new ExcelException("get OutputStream error!");
        }
    }

    /**
     * 导入Excel
     *
     * @param inputStream 输入流
     * @param clazz       泛型类型
     * @param listener    事件分析监听器
     * @return 是否成功导入excel文件
     */
    public static <T> Boolean readExcel(InputStream inputStream, Class<T> clazz, AnalysisEventListener<T> listener) {
        // 是否成功导入文件
        Boolean success = Boolean.TRUE;
        ExcelReader excelReader = null;
        try {
            // 通过EasyExcel工厂，实例化excelReader
            excelReader = EasyExcelFactory.read(inputStream, clazz, listener).build();
            // 实例化将要读取的excel sheet
            ReadSheet readSheet = EasyExcelFactory.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (Exception e) {
            log.error("Read excel error: {}", e.getMessage(), e);
            success = Boolean.FALSE;
        } finally {
            // 记得关闭excelReader
            if (excelReader != null) {
                excelReader.finish();
            }
        }
        return success;
    }

}
