package com.windf.minimalism.generation.model.template;

import com.windf.minimalism.generation.model.expand.ExpandItem;

import java.io.File;
import java.util.List;

/**
 * 代码生成的模板，用于定于代码生成的模板路径，以及自定义处理数据等
 */
public interface CodeTemplateHandler {

    /**
     * 返回模板的路径
     * @return
     */
    String getTemplatePath();

    /**
     * 获取所有拓展项
     * @return
     */
    List<ExpandItem> getExpandItems();

    /**
     * 获取处理文件程序
     */
    default List<CodeFileProcessor> getFileProcessor() {
        // 什么都不做
        return null;
    }
}
