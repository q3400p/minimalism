package com.windf.module.user.service.impl;

import com.windf.core.repository.ManageRepository;
import com.windf.plugin.service.business.BaseManageService;
import com.windf.module.user.entity.User;
import com.windf.module.user.repository.UserRepository;
import com.windf.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseManageService<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ManageRepository<User> getManageRepository() {
        return userRepository;
    }

}
