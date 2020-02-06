package com.windf.user.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.API_PATH)
public class UserController {
    public static final String API_PATH = "/user";
}
