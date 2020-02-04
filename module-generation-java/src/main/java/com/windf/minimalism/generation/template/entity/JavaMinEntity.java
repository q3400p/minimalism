package com.windf.minimalism.generation.template.entity;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;

public class JavaMinEntity extends Entity {
    /**
     * 获取class编号
     * @return
     */
    public String getClassCode() {
        return StringUtil.firstLetterUppercase(this.getCode());
    }
}
