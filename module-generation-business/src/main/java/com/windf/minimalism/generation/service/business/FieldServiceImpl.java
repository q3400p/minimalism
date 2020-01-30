package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.repository.FieldRepository;
import com.windf.minimalism.generation.service.FieldService;
import com.windf.minimalism.generation.service.TypeService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl extends BaseManageService<Field> implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private TypeService typeService;

    @Override
    public ManageRepository<Field> getManageRepository() {
        return fieldRepository;
    }

    @Override
    public void create(Field field) {
        String entityId = field.getEntity().getId();

        // 设置id
        field.setId(entityId + Entity.ID_POINT + field.getCode());

        // 设置类型
        String typeCode = field.getTypeCode();
        Type type = typeService.detail(typeCode);
        field.setType(type);

        super.create(field);
    }
}
