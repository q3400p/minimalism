package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Parameter;
import com.windf.minimalism.generation.repository.ParameterRepository;
import com.windf.minimalism.generation.service.ParameterService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceImpl extends BaseManageService<Parameter> implements ParameterService {

    private ParameterRepository parameterRepository;

    @Override
    public ManageRepository<Parameter> getManageRepository() {
        return parameterRepository;
    }
}
