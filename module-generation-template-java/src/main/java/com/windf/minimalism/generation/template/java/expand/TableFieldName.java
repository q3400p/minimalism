package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

public class TableFieldName implements ExpandItem<Field> {
    @Override
    public String getName() {
        return "数据库表名";
    }

    @Override
    public String getCode() {
        return "tableFieldName";
    }

    @Override
    public Type getType() {
        return LangType.Field.getType();
    }

    @Override
    public boolean isRequested() {
        return false;
    }

    @Override
    public Object getDefaultValue(Field field) {
        String typeId;

        // 如果是实体，生成外键名称
        if (field.getType() instanceof Entity) {
            String entityCode = field.getType().getCode();
            entityCode = StringUtil.splitCamelCase(entityCode, "_");
            typeId = "fk_" + entityCode + "_id";
        } else {
            typeId = StringUtil.splitCamelCase(field.getCode(), "_");
        }
        return typeId;
    }

    @Override
    public Class<Field> getExpandType() {
        return Field.class;
    }
}
