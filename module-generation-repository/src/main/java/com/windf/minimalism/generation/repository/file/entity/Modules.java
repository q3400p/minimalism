package com.windf.minimalism.generation.repository.file.entity;

import com.windf.core.exception.CodeException;
import com.windf.core.util.BeanUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.exception.EntityNotFountException;
import com.windf.minimalism.generation.exception.ModuleNotFountException;
import com.windf.minimalism.generation.repository.file.config.ModuleConfig;
import com.windf.plugin.repository.file.BaseJSONFileRepository;

import java.util.*;

public class Modules extends BaseJSONFileRepository {
    private static Modules modules = new Modules();

    public static Modules getInstance() {
        return modules;
    }

    /**
     * 模块List的PO，用于保存模块
     */
    private ModuleListPO moduleListPO;
    /**
     * 存放模块信息的对应关系
     */
    private Map<String, ModulePO> modulePOMap = new HashMap<>();

    private Modules() {
        // 读取json文件，获取模块列表
        this.readModuleList();
    }

    /**
     * 保存模块
     * @param modulePO
     */
    public void saveModule(ModulePO modulePO) {
        // 获取模块路径
        String modulePath = this.getModuleSavePath(modulePO.getId());

        // 保存文件
        this.write(modulePath, modulePO);

        // 写入缓存
        modulePOMap.put(modulePO.getId(), modulePO);

        // 保存模块列表
        this.updateModuleList(modulePO);
    }

    /**
     * 删除模块
     * @param ids
     */
    public void deleteModule(List<String> ids) {
        for (String id : ids) {
            // 读取文件
            String filePath = this.getModuleSavePath(id);

            // 删除文件
            boolean success = this.delete(filePath);

            // 删除缓存
            modulePOMap.remove(id);

            // 维护模块列表
            this.removeModuleFromList(id);

            // 如果文件不存在，抛出异常
            if (!success) {
                throw new ModuleNotFountException();
            }
        }
    }

    /**
     * 根据id获取模块
     * @param id
     */
    public ModulePO getModule(String id) {
        // 从map缓存总获取
        ModulePO modulePO = modulePOMap.get(id);
        if (modulePO != null) {
            return modulePO;
        }

        // 读取文件路径
        String filePath = this.getModuleSavePath(id);

        // 读取json文件
        modulePO = this.read(filePath, ModulePO.class);

        // 存储模块
        modulePOMap.put(id, modulePO);

        return modulePO;
    }

    /**
     * 获取所有模块
     * @return
     */
    public List<Module> listAllModules() {
        return moduleListPO.getModules();
    }

    // 在指定模块中添加实体
    public void saveEntity(Entity entity) {
        // 获取实体的模块
        Module module = entity.getModule();

        // 设置实体的module为空，不进行重复保存
        entity.setModule(null);

        // 获取模块
        ModulePO modulePO = this.getModule(module.getId());
        if (modulePO == null) {
            throw new ModuleNotFountException();
        }

        // 获取该模块的实体列表
        List<Entity> entities = modulePO.getEntities();
        if (entities == null) {
            entities = new ArrayList<>();
            modulePO.setEntities(entities);
        }

        // 找到模块
        boolean hasEntity = false;
        for (Entity e : entities) {
            // 如果找到对应的实体，进行修改
            if (e.getId().equals(entity.getId())) {
                // 复制内容
                BeanUtil.copyProperties(e, entity);
                // 已经找到模块，后面就不用添加了
                hasEntity = true;
                break;
            }
        }

        // 如果没有找到模块，添加新的实体
        if (!hasEntity) {
            entities.add(entity);
        }

        // 保存模块
        this.saveModule(modulePO);
    }

    public Entity getEntity(String id) {
        String moduleId = this.getParentId(id);

        ModulePO modulePO = this.getModule(moduleId);
        List<Entity> entities = modulePO.getEntities();

        // 如果没有实体，设置一个空的实体集合
        if (entities == null) {
            entities = new ArrayList<>();
            modulePO.setEntities(entities);
        }

        for (Entity entity : entities) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }

        return null;
    }

    /**
     * 删除实体
     * @param ids
     */
    public void deleteEntity(List<String> ids) {
        for (String id : ids) {
            String moduleId = this.getParentId(id);

            ModulePO modulePO = this.getModule(moduleId);
            if (modulePO == null) {
                throw new ModuleNotFountException();
            }

            // 遍历模块中的实体，进行删除
            List<Entity> entities = modulePO.getEntities();
            Iterator<Entity> iterator = entities.iterator();
            while (iterator.hasNext()) {
                Entity entity = iterator.next();

                // 匹配要删除的entity和id
                if (id.equals(entity.getId())) {
                    // 删除要删除的entity
                    iterator.remove();
                    break;
                }
            }

            // 保存模块
            this.saveModule(modulePO);
        }
    }


    /**
     * 保存字段
     * @param field
     */
    public void saveField(Field field) {
        // 获取实体id
        String entityId = field.getEntity().getId();

        // 设置实体，不进行保存
        field.setEntity(null);

        // 设置fieldId
        field.setId(entityId + Field.ID_POINT + field.getCode());

        // 获取实体
        Entity entity = this.getEntity(entityId);

        // 获取字段，如果没有，创建
        List<Field> fields = entity.getFields();
        if (fields == null) {
            fields = new ArrayList<>();
            entity.setFields(fields);
        }

        // 去当前实体的字段中，寻找字段，进行修改
        boolean hasField = false;
        for (Field f : fields) {
            if (f.getId().equals(field.getId())) {
                BeanUtil.copyProperties(f, field);
                hasField = true;
                break;
            }
        }

        // 如果没有找到字段，进行添加
        if (!hasField) {
            fields.add(field);
        }

        // 保存模块
        this.saveModule(modulePOMap.get(this.getParentId(entityId)));
    }

    /**
     * 获取字段
     * @param id
     * @return
     */
    public Field getField(String id) {
        // 获取实体
        String entityId = this.getParentId(id);
        Entity entity = this.getEntity(entityId);

        // 去当前实体的字段中，寻找字段，进行修改
        List<Field> fields = entity.getFields();
        for (Field f : fields) {
            if (f.getId().equals(id)) {
                return f;
            }
        }

        return null;
    }

    /**
     * 删除字段
     * @param ids
     */
    public void deleteField(List<String> ids) {
        for (String id : ids) {
            String entityId = this.getParentId(id);

            Entity entity = this.getEntity(entityId);
            if (entity == null) {
                throw new EntityNotFountException();
            }

            // 遍历模块中的实体，进行删除
            List<Field> fields = entity.getFields();
            Iterator<Field> iterator = fields.iterator();
            while (iterator.hasNext()) {
                Field field = iterator.next();

                // 匹配要删除的entity和id
                if (id.equals(field.getId())) {
                    // 删除要删除的entity
                    iterator.remove();
                    break;
                }
            }

            // 保存模块
            this.saveModule(modulePOMap.get(this.getParentId(entityId)));
        }
    }

    /**
     * 保存方法
     * @param method
     */
    public void saveMethod(Method method) {
        // 获取实体id
        String entityId = method.getEntity().getId();

        // 设置实体，不进行保存
        method.setEntity(null);

        // 设置methodId
        method.setId(entityId + Field.ID_POINT + method.getCode());

        // 获取实体
        Entity entity = this.getEntity(entityId);
        if (entity == null) {
            throw new EntityNotFountException();
        }

        // 获取字段，如果没有，创建
        List<Method> methods = entity.getMethods();
        if (methods == null) {
            methods = new ArrayList<>();
            entity.setMethods(methods);
        }

        // 去当前实体的字段中，寻找字段，进行修改
        boolean hasField = false;
        for (Method m : methods) {
            if (m.getId().equals(method.getId())) {
                BeanUtil.copyProperties(m, method);
                hasField = true;
                break;
            }
        }

        // 如果没有找到字段，进行添加
        if (!hasField) {
            methods.add(method);
        }

        // 保存模块
        this.saveModule(modulePOMap.get(this.getParentId(entityId)));
    }

    /**
     * 获取方法
     * @param id
     * @return
     */
    public Method getMethod(String id) {
        // 获取实体
        String entityId = this.getParentId(id);
        Entity entity = this.getEntity(entityId);

        // 去当前实体的方法中，寻找方法，进行修改
        List<Method> methods = entity.getMethods();
        for (Method m : methods) {
            if (m.getId().equals(id)) {
                return m;
            }
        }

        return null;
    }

    /**
     * 删除方法
     * @param ids
     */
    public void deleteMethod(List<String> ids) {
        for (String id : ids) {
            String entityId = this.getParentId(id);

            Entity entity = this.getEntity(entityId);
            if (entity == null) {
                throw new EntityNotFountException();
            }

            // 遍历模块中的实体，进行删除
            List<Method> methods = entity.getMethods();
            Iterator<Method> iterator = methods.iterator();
            while (iterator.hasNext()) {
                Method method = iterator.next();

                // 匹配要删除的method和id
                if (id.equals(method.getId())) {
                    // 删除要删除的method
                    iterator.remove();
                    break;
                }
            }

            // 保存模块
            this.saveModule(modulePOMap.get(this.getParentId(entityId)));
        }
    }


    /**
     * 读取文件列表
     */
    private void readModuleList() {
        // 读取列表文件路径
        String filePath = this.getModuleListFile();
        // 读取文件内容
        moduleListPO = this.read(filePath, ModuleListPO.class);
        // 如果获取不到，新创建一个
        if (moduleListPO == null) {
            moduleListPO = new ModuleListPO();
        }
    }

    /**
     * 修改文件列表中的模块，并且持久化到文件中
     * @param module
     */
    private void updateModuleList(ModulePO module) {
        // 先读取模块列表
        this.readModuleList();

        List<Module> modules = moduleListPO.getModules();
        boolean moduleUnSaved = true;
        for (Module dbModule : modules) {
            if (dbModule.getId().equals(module.getId())) {
                // 复制bean的树形到db中，这里的db是文件系统
                BeanUtil.copyProperties(dbModule, module);
                // 标记已经找到模块
                moduleUnSaved = false;
                break;
            }
        }

        // 如果模块没有保存，添加到list中
        if (moduleUnSaved) {
            modules.add(module);
        }

        this.saveModuleList();
    }

    /**
     * 存储模块列表
     */
    private void saveModuleList() {
        // 读取列表文件路径
        String filePath = this.getModuleListFile();
        // 保存文件
        this.write(filePath, moduleListPO);
    }

    /**
     * 从模块列表中，删除指定id的模块，并且进行持久化
     * @param id
     */
    private void removeModuleFromList(String id) {
        Iterator<Module> iterator = moduleListPO.getModules().iterator();
        while (iterator.hasNext()) {
            Module module = iterator.next();
            if (module.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }

        // 模块列表持久化
        this.saveModuleList();
    }

    /**
     * 获取模块的保存路径
     * @param moduleId
     * @return
     */
    protected String getModuleSavePath(String moduleId) {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                moduleId + "." + ModuleConfig.getInstance().getFileSuffix();
    }

    /**
     * 获取模块列表
     * @return
     */
    protected String getModuleListFile() {
        return ModuleConfig.getInstance().getModulePath() + "/" +
                ModuleConfig.getInstance().getModuleListFileName();
    }

    /**
     * 根据获取父级id
     * @param id
     * @return
     */
    protected String getParentId(String id) {
        int lastPoint = id.lastIndexOf(".");
        if (lastPoint == -1) {
            throw new CodeException("找不到" + id + "对应的父级id");
        }
        return id.substring(0, lastPoint);
    }
}
