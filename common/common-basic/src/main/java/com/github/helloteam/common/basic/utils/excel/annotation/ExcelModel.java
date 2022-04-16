package com.github.helloteam.common.basic.utils.excel.annotation;

import java.lang.annotation.*;

/**
 * Excel Model注解
 *
 * @author zdz
 * @date 2022/04/16 11:13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelModel {

	/**
	 * 模块名，用于导出时的文件名
	 * @return 导出文件名
	 */
	String value() default "";

	/**
	 * 页名
	 * @return 页名
	 */
	String[] sheets() default {"sheet1"};

}
