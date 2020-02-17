package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.core.util.CollectionUtil;
import com.windf.minimalism.generation.entity.*;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.model.expand.ExpandItemManager;
import com.windf.minimalism.generation.model.expand.ExpandItemManagerProcess;
import com.windf.minimalism.generation.repository.MethodRepository;
import com.windf.minimalism.generation.service.MethodService;
import com.windf.minimalism.generation.service.TypeService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodServiceImpl extends BaseManageService<Method> implements MethodService {

    @Autowired
    private MethodRepository methodRepository;

    @Autowired
    private TypeService typeService;

    @Override
    public ManageRepository<Method> getManageRepository() {
        return methodRepository;
    }

    @Override
    public void create(Method method) {
        String entityId = method.getEntity().getId();

        // 设置id
        method.setId(entityId + Entity.ID_POINT + method.getCode());

        // 设置的参数id
        List<Parameter> parameterList = method.getParameters();
        if (CollectionUtil.isNotEmpty(parameterList)) {
            for (int i = 0; i < parameterList.size(); i++) {
                Parameter parameter = parameterList.get(i);
                parameter.setId(method.getId() + Parameter.ID_POINT + i + Parameter.PARAM_NUMBER_POINT + parameter.getId());
            }
        }

        super.create(method);
    }

    @Override
    public Method detail(String id) {
        Method method = super.detail(id);

        // 补全type TODO 总是这样设置不好吧
        if (method != null) {
            // 根据返回值的id设置返回值类型
            Return methodReturn = method.getMethodReturn();
            if (methodReturn != null) {
                methodReturn.setType(this.typeService.detail(methodReturn.getType().getId()));
            }

            // 根据参数id设置参数
            List<Parameter> parameterList = method.getParameters();
            if (CollectionUtil.isNotEmpty(parameterList)) {
                for (int i = 0; i < parameterList.size(); i++) {
                    Parameter parameter = parameterList.get(i);
                    parameter.setType(this.typeService.detail(parameter.getType().getId()));
                }
            }
        }

        return method;
    }

    @Override
    public List<ExpandItem> getExpandItemList() {
        return ExpandItemManagerProcess.getInstance().getExpandItemList(Method.class);
    }

    @Override
    public ExpandItem getExpandItem(String code) {
        return ExpandItemManagerProcess.getInstance().getExpandItem(Method.class, code);
    }
}
