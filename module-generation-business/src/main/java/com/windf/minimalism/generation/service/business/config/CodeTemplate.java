package com.windf.minimalism.generation.service.business.config;

public class CodeTemplate {
    /**
     * 模板路径的位置
     */
    private String templatePath = System.getProperty("user.home") + "/.windf/template";

    /**
     * 代码的生成位置
     */
    private String targetPath = System.getProperty("user.home") + "/.windf/target";

    public String getTemplatePath() {
        return this.templatePath;
    }

    public String getTargetPath() {
        return targetPath;
    }
}
