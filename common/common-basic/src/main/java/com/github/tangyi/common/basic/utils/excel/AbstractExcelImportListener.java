package com.github.tangyi.common.basic.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装简单数据导入的逻辑，解析3000条刷一次数据库
 *
 * @author zdz
 * @date 2022/04/16 11:25
 */
public abstract class AbstractExcelImportListener<T> extends AnalysisEventListener<T> {

    /**
     * 日志生成器
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 批处理数目，表示每处理 BATCH_COUNT 条数据时，存储数据库
     */
    private static final int BATCH_COUNT = 3000;

    /**
     * 需要导入的数据
     */
    private List<T> dataList = new ArrayList<>();

    /**
     * 读取数据
     *
     * @param dataModel 数据模型
     * @param context   分析上下文
     */
    @Override
    public void invoke(T dataModel, AnalysisContext context) {
        dataList.add(dataModel);
        // 达到BATCH_COUNT则保存进数据库，防止数据几万条数据在内存，容易OOM
        if (dataList.size() >= BATCH_COUNT) {
            saveData(dataList);
            dataList.clear();
        }
    }

    /**
     * 解析所有数据之后，进行最后一次的保存操作
     *
     * @param context 分析上下文，用于确认是否解析完所有数据
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData(dataList);
        logger.info("All data is parsed!");
    }

    /**
     * 保存数据，子类实现
     */
    public abstract void saveData(List<T> dataList);

}
