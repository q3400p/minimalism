package com.windf.minimalism.generation.exception;

import com.windf.core.exception.UserException;

public class ModuleNotFountException extends UserException {
    /**
     * 模块没有找到的异常编号
     */
    public static final String TYPE_CODE = "404";

    public ModuleNotFountException() {
        super(TYPE_CODE, "模块找不到");
    }
}
