package com.windf.minimalism.generation.template.java;

import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.model.template.CodeFileProcessor;
import com.windf.minimalism.generation.model.template.CodeTemplateHandler;
import com.windf.minimalism.generation.template.java.expand.ClassCode;
import com.windf.minimalism.generation.template.java.expand.ClassId;
import com.windf.minimalism.generation.template.java.expand.TableFieldName;
import com.windf.minimalism.generation.template.java.expand.TableName;
import com.windf.minimalism.generation.template.java.processor.ImportProcessor;
import com.windf.minimalism.generation.template.java.processor.MultipleLIneProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * java的代码生成的处理程序
 */
public abstract class JavaCodeTemplateHandler implements CodeTemplateHandler {
    @Override
    public List<ExpandItem> getExpandItems() {
        List<ExpandItem> result = new ArrayList<>();
        result.add(new ClassCode());
        result.add(new ClassId());
        result.add(new TableName());
        result.add(new TableFieldName());
        return result;
    }

    @Override
    public List<CodeFileProcessor> getFileProcessor() {
        List<CodeFileProcessor> result = new ArrayList<>();
        // 处理导入
        result.add(new ImportProcessor());
        // 去掉空行
        result.add(new MultipleLIneProcessor());
        return result;
    }
}
