package com.windf.minimalism.generation.template.entity;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;

public class JavaMinField extends Field {
    public String getMethodCode() {
        return StringUtil.firstLetterUppercase(this.getCode());
    }
}
