package com.github.tangyi.user.utils;

import com.github.tangyi.user.api.module.Menu;
import com.github.tangyi.user.excel.model.MenuExcelModel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单工具类
 *
 * @author zdz
 * @date 2022/04/16 11:37
 */
public class MenuUtil {

    /**
     * 私有化无参构造器
     */
    private MenuUtil() {
    }

    /**
     * 将菜单列表转换为菜单excelModel列表
     *
     * @param menus 菜单列表
     * @return 包含菜单excelModel的列表
     */
    public static List<MenuExcelModel> convertToExcelModel(List<Menu> menus) {
        List<MenuExcelModel> menuExcelModels = new ArrayList<>(menus.size());
        menus.forEach(menu -> {
            MenuExcelModel menuExcelModel = new MenuExcelModel();
            BeanUtils.copyProperties(menu, menuExcelModel);
            menuExcelModels.add(menuExcelModel);
        });
        return menuExcelModels;
    }

}
