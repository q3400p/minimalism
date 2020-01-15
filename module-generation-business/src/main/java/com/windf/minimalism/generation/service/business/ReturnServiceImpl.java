package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Return;
import com.windf.minimalism.generation.repository.ReturnRepository;
import com.windf.minimalism.generation.service.ReturnService;
import com.windf.plugin.service.business.BaseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnServiceImpl extends BaseManageService<Return> implements ReturnService {

    private ReturnRepository returnRepository;

    @Override
    public ManageRepository<Return> getManageRepository() {
        return returnRepository;
    }
}
