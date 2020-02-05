package com.windf.minimalism.generation.template.entity;

import com.windf.core.util.CollectionUtil;
import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.*;

import java.util.*;

public class JavaImport {
    private Entity entity;

    private Set<String> imports = new HashSet<>();

    public JavaImport(Entity entity) {
        this.entity = entity;
    }

    /**
     * 获取导入的包
     * @param packageId 要排除的packageId
     * @return
     */
    public List<String> getImport(String packageId) {
        // 构建导入
        this.buildImport();

        // 转换为list
        List<String> importList = new ArrayList<>(imports);

        // 排除packageId
        this.exportPackage(importList, packageId);

        // 排序
        Collections.sort(importList);

        return importList;
    }

    /**
     * 构建导入
     */
    private void buildImport() {
        // 构建方法导入
        this.buildMethodImport();
        // 构建字段导入
        this.buildFieldImport();
    }

    /**
     * 构建方法导入
     */
    private void buildMethodImport() {
        if (entity == null || CollectionUtil.isEmpty(entity.getMethods())) {
            return;
        }

        for (Method m : entity.getMethods()) {
            // 导入返回值
            this.buildTypeImport(m.getMethodReturn().getType());

            // 导入参数列表
            List<Parameter> parameters = m.getParameters();
            if (CollectionUtil.isNotEmpty(parameters)) {
                for (Parameter parameter : parameters) {
                    this.buildTypeImport(parameter.getType());
                }
            }
        }
    }

    /**
     * 构建字段导入
     */
    private void buildFieldImport() {
        if (entity == null || CollectionUtil.isEmpty(entity.getFields())) {
            return;
        }

        for (Field field : entity.getFields()) {
            this.buildTypeImport(field.getType());
        }
    }

    /**
     * 构建导入
     */
    private void buildTypeImport(Type type) {
        // 验证参数
        if (type == null || StringUtil.isEmpty(type.getId())) {
            return;
        }

        String id = type.getId();

        // lang包中的不导入
        if (id.startsWith("java.lang.")) {
            return;
        }

        imports.add(id);
    }

    /**
     * 排除指定包下的实体
     * @param imports
     * @param packageId
     * @return
     */
    private void exportPackage(List<String> imports, String packageId) {
        // 如果没有要排除的，全部返回
        if (StringUtil.isEmpty(packageId)) {
            return;
        }

        if (CollectionUtil.isNotEmpty(imports)) {
            Iterator<String> iterator = imports.iterator();
            while (iterator.hasNext()) {
                String impId = iterator.next();

                // 如果引入的id的，和package是同包的，排除掉
                String impPackage = impId.substring(0, impId.lastIndexOf("."));
                if (packageId.equals(impPackage)) {
                    iterator.remove();
                }
            }
        }
    }
}
