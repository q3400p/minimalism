package com.windf.minimalism.generation.template.java.windf;

import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.template.java.JavaCodeTemplateHandler;
import com.windf.minimalism.generation.template.java.windf.expand.ManageAble;

import java.util.List;

/**
 * 模板处理程序
 */
public class WindfJavaCodeTemplateHandler extends JavaCodeTemplateHandler {
    @Override
    public String getTemplatePath() {
        return "/template/java/windf";
    }

    @Override
    public List<ExpandItem> getExpandItems() {
        List<ExpandItem> result = super.getExpandItems();
        result.add(new ManageAble());
        return result;
    }
}
