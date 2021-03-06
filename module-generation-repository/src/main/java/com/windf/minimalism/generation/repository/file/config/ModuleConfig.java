package com.windf.minimalism.generation.repository.file.config;

public class ModuleConfig {

    public static ModuleConfig getInstance() {
        return new ModuleConfig();
    }

    /**
     * 模块路径，用于保存路径
     */
    private String modulePath = "/module";

    /**
     * moduleList文件路径，用于保存模块路径
     */
    private String moduleListFileName = "modules.json";

    /**
     * 保存文件的后缀名
     */
    private String fileSuffix = "json";

    public String getModulePath() {
        return modulePath;
    }

    public String getModuleListFileName() {
        return moduleListFileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }
}
