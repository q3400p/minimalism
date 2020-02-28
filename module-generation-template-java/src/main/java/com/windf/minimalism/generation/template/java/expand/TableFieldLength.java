package com.windf.minimalism.generation.template.java.expand;

import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TableFieldLength implements ExpandItem<Field> {
    private static final Logger logger = Logger.getLogger(TableFieldLength.class);

    private Map<String, String> tableFieldLengthMap = new HashMap<>();
    public TableFieldLength() {
        tableFieldLengthMap.put(LangType.VOID.getCode(), null);

        tableFieldLengthMap.put(LangType.INTEGER.getCode(), "11");
        tableFieldLengthMap.put(LangType.Score.getCode(), "4,2");
        tableFieldLengthMap.put(LangType.Money.getCode(), "20,5");
        tableFieldLengthMap.put(LangType.Boolean.getCode(), "1");

        tableFieldLengthMap.put(LangType.Password.getCode(), "32");
        tableFieldLengthMap.put(LangType.Phone.getCode(), "20");
        tableFieldLengthMap.put(LangType.IdCard.getCode(), "20");

        tableFieldLengthMap.put(LangType.Redio.getCode(), "50");
        tableFieldLengthMap.put(LangType.Checkbox.getCode(), "50");
        tableFieldLengthMap.put(LangType.Combox.getCode(), "50");

        tableFieldLengthMap.put(LangType.Date.getCode(), null);
        tableFieldLengthMap.put(LangType.Time.getCode(), null);
        tableFieldLengthMap.put(LangType.Datetime.getCode(), null);

        tableFieldLengthMap.put(LangType.Field.getCode(), "255");
        tableFieldLengthMap.put(LangType.TextArea.getCode(), "2000");
        tableFieldLengthMap.put(LangType.TextEditor.getCode(), null);

        tableFieldLengthMap.put(LangType.File.getCode(), "255");
        tableFieldLengthMap.put(LangType.Image.getCode(), "255");
        tableFieldLengthMap.put(LangType.Audio.getCode(), "255");
        tableFieldLengthMap.put(LangType.Video.getCode(), "255");
    }

    @Override
    public String getName() {
        return "数据库字段长度";
    }

    @Override
    public String getCode() {
        return "tableFieldLength";
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
        // 如果是实体，设置类型为field TODO 其实应该获得该实体的id长度
        if (field.getType() instanceof Entity)  {
            return "255";
        }
        logger.info(field.getId());
        return tableFieldLengthMap.get(field.getType().getCode());
    }

    @Override
    public Class<Field> getExpandType() {
        return Field.class;
    }
}
