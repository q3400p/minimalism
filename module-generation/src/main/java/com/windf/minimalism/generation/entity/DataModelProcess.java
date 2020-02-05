package com.windf.minimalism.generation.entity;

import com.windf.minimalism.generation.service.DataModelHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class DataModelProcess implements DataModelHandler {

    private static DataModelProcess dataModelProcess = new DataModelProcess();

    public static DataModelProcess getInstance() {
        return dataModelProcess;
    }

    private List<DataModelHandler> handlers = new ArrayList<>();

    private DataModelProcess() {
        this.reload();
    }

    @Override
    public Module processModule(Module module) {
        for (DataModelHandler dataModelHandler : handlers) {
            module = dataModelHandler.processModule(module);
        }
        return module;
    }

    @Override
    public Entity processEntity(Entity entity) {
        for (DataModelHandler dataModelHandler : handlers) {
            entity = dataModelHandler.processEntity(entity);
        }
        return entity;
    }

    /**
     * 获取所有数据模型
     * @return
     */
    public void reload() {
        ServiceLoader<DataModelHandler> handlerList = ServiceLoader.load(DataModelHandler.class);

        if (handlerList != null) {
            for (DataModelHandler handler : handlerList) {
                this.handlers.add(handler);
            }
        }
    }
}
