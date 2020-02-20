package com.windf.minimalism.generation.template.java.expand;

import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.LangType;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.template.java.util.ClassUtil;

import java.util.HashMap;
import java.util.Map;

public class ClassTypeId implements ExpandItem<Type> {
    private Map<String, String> javaTypeMap = new HashMap<>();
    public ClassTypeId() {
        javaTypeMap.put(LangType.VOID.getCode(), "void");

        javaTypeMap.put(LangType.INTEGER.getCode(), "java.lang.Integer");
        javaTypeMap.put(LangType.Score.getCode(), "java.lang.Float");
        javaTypeMap.put(LangType.Money.getCode(), "java.math.BigDecimal");
        javaTypeMap.put(LangType.Boolean.getCode(), "java.lang.Boolean");

        javaTypeMap.put(LangType.Password.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.Phone.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.IdCard.getCode(), "java.lang.String");

        javaTypeMap.put(LangType.Redio.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.Checkbox.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.Combox.getCode(), "java.lang.String");

        javaTypeMap.put(LangType.Date.getCode(), "java.util.Date");
        javaTypeMap.put(LangType.Time.getCode(), "java.util.Date");
        javaTypeMap.put(LangType.Datetime.getCode(), "java.util.Date");

        javaTypeMap.put(LangType.Field.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.Textarea.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.TextEditor.getCode(), "java.lang.String");

        javaTypeMap.put(LangType.File.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.Image.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.Audio.getCode(), "java.lang.String");
        javaTypeMap.put(LangType.Video.getCode(), "java.lang.String");
    }

    @Override
    public String getName() {
        return "类型的类全名";
    }

    @Override
    public String getCode() {
        return "classTypeId";
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
    public Object getDefaultValue(Type expandSlot) {
        String importClassId;
        String classCode;
        if (expandSlot instanceof Entity) {
            classCode = StringUtil.firstLetterUppercase(expandSlot.getCode());
            Entity entity = ((Entity) expandSlot);
            Module module = entity.getModule();
            importClassId = module.getNamespace() + ".module." + module.getCode() + ".entity." + classCode;
        } else {
            importClassId = javaTypeMap.get(expandSlot.getCode());
            classCode = ClassUtil.getImportClassCode(importClassId);
        }

        return "[package||" + importClassId + "||" + classCode + "]";
    }

    @Override
    public Class<Type> getExpandType() {
        return Type.class;
    }
}
