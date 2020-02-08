package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.model.expand.EntityExpandItem;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.model.expand.ExpandItemListProcess;
import com.windf.minimalism.generation.repository.EntityRepository;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.minimalism.generation.service.FieldService;
import com.windf.minimalism.generation.service.MethodService;
import com.windf.minimalism.generation.service.TypeService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityServiceImpl extends BaseManageService<Entity> implements EntityService {

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private FieldService fieldService;

    @Override
    public ManageRepository<Entity> getManageRepository() {
        return entityRepository;
    }

    @Override
    public void create(Entity entity) {
        String moduleId = entity.getModule().getId();

        // 设置id
        entity.setId(moduleId + Entity.ID_POINT + entity.getCode());

        super.create(entity);

        // 将新增的实体，更新到类型中
        typeService.addEntity(entity);
    }

    @Override
    public List<Type> listAllEntityType() {
        return entityRepository.listAllEntityType();
    }

    @Override
    public List<Entity> getByModuleId(String moduleId) {
        return this.entityRepository.getByModuleId(moduleId);
    }

    @Override
    public Entity detail(String id) {
        Entity entity = super.detail(id);

        if (entity == null) {
            return null;
        }

        // TODO 这些设置有点麻烦
        if (entity.getMethods() != null) {
            List<Method> methods = new ArrayList<>(entity.getMethods().size());
            for (Method method : entity.getMethods()) {
                methods.add(methodService.detail(method.getId()));
            }
            entity.setMethods(methods);
        }
        if (entity.getFields() != null) {
            List<Field> fields = new ArrayList<>(entity.getFields().size());
            for (Field field : entity.getFields()) {
                fields.add(fieldService.detail(field.getId()));
            }
            entity.setFields(fields);
        }

        return entity;
    }

    @Override
    public List<ExpandItem> getExpandItemList() {
        return ExpandItemListProcess.getInstance().getExpandItemList(EntityExpandItem.class);
    }

    @Override
    public ExpandItem getExpandItem(String code) {
        return ExpandItemListProcess.getInstance().getExpandItem(EntityExpandItem.class, code);
    }
}
