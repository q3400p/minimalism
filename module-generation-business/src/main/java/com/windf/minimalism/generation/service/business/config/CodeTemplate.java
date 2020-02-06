package com.windf.minimalism.generation.service.business.config;

public class CodeTemplate {

    /**
     * 代码的生成位置
     */
    private String targetPath = System.getProperty("user.home") + "/.windf/target";

    /**
     * 定义文件的结尾符号
     */
    private String defineFileExt = ".wdf";

    public String getTargetPath() {
        return targetPath;
    }

    public String getDefineFileExt() {
        return defineFileExt;
    }
}
