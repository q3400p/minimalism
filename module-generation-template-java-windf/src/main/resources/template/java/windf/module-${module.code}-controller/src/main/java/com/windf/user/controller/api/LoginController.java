package com.windf.user.controller.api;

import com.windf.core.entity.ResultData;
import com.windf.module.user.entity.Login;
import com.windf.module.user.entity.User;
import com.windf.module.user.service.LoginService;
import com.windf.plugin.controller.api.controller.BaseController;
import com.windf.user.controller.api.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LoginController.API_PATH)
public class LoginController extends BaseController {
    public static final String API_PATH = "/login";

    @Autowired
    private LoginService loginService;

    @PostMapping({"/", ""})
    public ResultData login(Login login) {
        User user = loginService.login(login);

        getSession().set(Constant.KEY_LOGIN_USER, user);

        return response().success();
    }

    @RequestMapping("/logout")
    public ResultData logout() {
        loginService.logout();
        return response().success();
    }

    @GetMapping("/user")
    public ResultData users() {
        return response().successData(getSession().get(Constant.KEY_LOGIN_USER));
    }
}
