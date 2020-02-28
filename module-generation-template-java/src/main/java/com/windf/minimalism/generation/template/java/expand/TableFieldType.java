package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;

import java.util.HashMap;
import java.util.Map;

public class TableFieldType implements ExpandItem<Field> {
    private Map<String, String> tableFieldTypeMap = new HashMap<>();
    public TableFieldType() {
        tableFieldTypeMap.put(LangType.VOID.getCode(), "null");

        tableFieldTypeMap.put(LangType.INTEGER.getCode(), "int");
        tableFieldTypeMap.put(LangType.Score.getCode(), "decimal");
        tableFieldTypeMap.put(LangType.Money.getCode(), "decimal");
        tableFieldTypeMap.put(LangType.Boolean.getCode(), "char");

        tableFieldTypeMap.put(LangType.Password.getCode(), "char");
        tableFieldTypeMap.put(LangType.Phone.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.IdCard.getCode(), "varchar");

        tableFieldTypeMap.put(LangType.Redio.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.Checkbox.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.Combox.getCode(), "varchar");

        tableFieldTypeMap.put(LangType.Date.getCode(), "date");
        tableFieldTypeMap.put(LangType.Time.getCode(), "time");
        tableFieldTypeMap.put(LangType.Datetime.getCode(), "datetime");

        tableFieldTypeMap.put(LangType.Field.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.TextArea.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.TextEditor.getCode(), "text");

        tableFieldTypeMap.put(LangType.File.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.Image.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.Audio.getCode(), "varchar");
        tableFieldTypeMap.put(LangType.Video.getCode(), "varchar");
    }

    @Override
    public String getName() {
        return "数据库字段类型";
    }

    @Override
    public String getCode() {
        return "tableFieldType";
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
        // 如果是实体，设置类型为field TODO 其实应该获得该实体的id类型
        if (field.getType() instanceof Entity)  {
            return tableFieldTypeMap.get(LangType.Field.getCode());
        }
        return tableFieldTypeMap.get(field.getType().getCode());
    }

    @Override
    public Class<Field> getExpandType() {
        return Field.class;
    }
}
