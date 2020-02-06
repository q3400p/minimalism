package com.windf.minimalism.generation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class CodeTemplateHandlerProcess {

    private static CodeTemplateHandlerProcess dataModelProcess = new CodeTemplateHandlerProcess();

    public static CodeTemplateHandlerProcess getInstance() {
        return dataModelProcess;
    }

    private List<CodeTemplateHandler> handlers = new ArrayList<>();

    private CodeTemplateHandlerProcess() {
        this.reload();
    }

    public List<CodeTemplateHandler> getAllCodeTemplateHandler() {
        return handlers;
    }

    /**
     * 加载所有模板
     * @return
     */
    public void reload() {
        ServiceLoader<CodeTemplateHandler> handlerList = ServiceLoader.load(CodeTemplateHandler.class);

        if (handlerList != null) {
            for (CodeTemplateHandler handler : handlerList) {
                handlers.add(handler);
            }
        }
    }
}
