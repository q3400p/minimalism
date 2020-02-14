package com.windf.minimalism.generation.template.java.processor;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.model.template.CodeFileProcessor;

import java.util.Iterator;
import java.util.List;

public class MultipleLIneProcessor implements CodeFileProcessor {
    @Override
    public void process(List<String> lineList) {
        // 解析多个空行
        boolean frontLineIsEmpty = false;
        Iterator<String> iterator = lineList.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();

            // 寻找空行
            if (StringUtil.isBlank(line)) {
                // 如果上一个还是空行，不要这一行了
                if (frontLineIsEmpty) {
                    iterator.remove();
                } else {
                    frontLineIsEmpty = true;
                }
            } else {
                // 如果发现非空行，标记前一个为空行
                frontLineIsEmpty = false;
            }
        }
    }
}
