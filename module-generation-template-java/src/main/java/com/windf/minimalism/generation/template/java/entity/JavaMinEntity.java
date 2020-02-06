package com.windf.minimalism.generation.template.java.entity;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;

import java.util.List;

public class JavaMinEntity extends Entity {
    /**
     * 获取class编号
     * @return
     */
    public String getClassCode() {
        return StringUtil.firstLetterUppercase(this.getCode());
    }

    /**
     * 获得导入
     * @return
     */
    public List<String> imports(String packageId) {
        return new JavaImport(this).getImport(packageId);
    }
}
