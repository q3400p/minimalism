package com.windf.minimalism.generation.service.business;

import com.windf.minimalism.generation.entity.Type;
import com.windf.minimalism.generation.entity.type.LangType;
import com.windf.minimalism.generation.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    @Override
    public List<Type> listAll() {
        return null;
    }

    @Override
    public Type detail(String id) {
        return null;
    }

    private void listAllBaseType() {
        LangType[] langTypes = LangType.class.getEnumConstants();
        for (LangType langType : langTypes) {
            System.out.println(langType.getCode() + ":" + langType.getName());
        }
    }

    public static void main(String[] args) {
        new TypeServiceImpl().listAllBaseType();
    }
}
