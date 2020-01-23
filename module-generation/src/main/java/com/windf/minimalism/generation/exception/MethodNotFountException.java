package com.windf.minimalism.generation.exception;

import com.windf.core.exception.UserException;

public class MethodNotFountException extends UserException {
    /**
     * 方法没有找到的异常编号
     */
    public static final String TYPE_CODE = "404";

    public MethodNotFountException() {
        super(TYPE_CODE, "方法找不到");
    }
}
