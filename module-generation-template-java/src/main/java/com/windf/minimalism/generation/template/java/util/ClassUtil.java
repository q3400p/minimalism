package com.windf.minimalism.generation.template.java.util;

import com.windf.core.util.StringUtil;

public class ClassUtil {
    /**
     * 获取class的全路径
     * 就是最后一个点的后面的单词首字母大写
     * @param classId
     * @return
     */
    public static String getImportClassId(String classId) {
        if (!classId.contains(".")) {
            return classId;
        }

        String typeNamespace = classId.substring(0, classId.lastIndexOf("."));
        String typeCode = StringUtil.firstLetterUppercase(classId.substring(classId.lastIndexOf(".") + 1));
        String importTypeId = typeNamespace + "." + typeCode;
        return importTypeId;
    }

}
