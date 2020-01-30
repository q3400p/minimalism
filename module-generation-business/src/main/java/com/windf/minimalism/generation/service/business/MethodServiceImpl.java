package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.core.util.CollectionUtil;
import com.windf.minimalism.generation.entity.*;
import com.windf.minimalism.generation.repository.MethodRepository;
import com.windf.minimalism.generation.service.MethodService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        // 设置返回值的id
        Return methodReturn = method.getMethodReturn();
        if (methodReturn != null) {
            methodReturn.setId(method.getId() + Return.ID_POINT + methodReturn.getTypeCode());
        }

        // 设置参数id
        List<Parameter> parameterList = method.getParameters();
        if (CollectionUtil.isNotEmpty(parameterList)) {
            for (int i = 0; i < parameterList.size(); i++) {
                Parameter parameter = parameterList.get(i);
                parameter.setId(method.getId() + Parameter.ID_POINT + i + Parameter.PARAM_NUMBER_POINT + parameter.getTypeCode());
            }
        }

        super.create(method);
    }
}
