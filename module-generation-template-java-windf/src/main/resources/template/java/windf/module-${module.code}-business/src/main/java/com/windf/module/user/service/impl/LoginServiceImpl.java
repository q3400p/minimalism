package com.windf.module.user.service.impl;

import com.windf.core.util.EncryptUtil;
import com.windf.module.user.entity.Login;
import com.windf.module.user.entity.User;
import com.windf.module.user.exception.LoginException;
import com.windf.module.user.repository.LoginRepository;
import com.windf.module.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public User login(Login login) {
        User user = loginRepository.getByUsername(login.getUsername());

        String inputPassword = login.getPassword();

        if (user == null || !inputPassword.equals(user.getPassword())) {
            throw new LoginException();
        }

        doLogin(user);

        return user;
    }

    @Override
    public void doLogin(User user) {

    }

    @Override
    public void logout() {

    }
}
