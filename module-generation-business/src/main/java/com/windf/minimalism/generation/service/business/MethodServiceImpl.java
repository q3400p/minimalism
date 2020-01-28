package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.entity.Method;
import com.windf.minimalism.generation.repository.MethodRepository;
import com.windf.minimalism.generation.service.MethodService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodServiceImpl extends BaseManageService<Method> implements MethodService {

    @Autowired
    private MethodRepository methodRepository;

    @Override
    public ManageRepository<Method> getManageRepository() {
        return methodRepository;
    }

    @Override
    public void create(Method method) {
        String entityId = method.getEntity().getId();

        // 设置id
        method.setId(entityId + Entity.ID_POINT + method.getCode());

        super.create(method);
    }
}
