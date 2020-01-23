package com.windf.minimalism.generation.exception;

import com.windf.core.exception.UserException;

public class EntityNotFountException extends UserException {
    /**
     * 实体没有找到的异常编号
     */
    public static final String TYPE_CODE = "404";

    public EntityNotFountException() {
        super(TYPE_CODE, "模块的实体找不到");
    }
}
