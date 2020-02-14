package com.windf.minimalism.generation.model.template;

import java.util.List;

/**
 * 文件处理
 */
public interface CodeFileProcessor {
    /**
     * 处理文件信息
     * @param lineList
     */
    void process(List<String> lineList);
}
