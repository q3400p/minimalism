package com.windf.minimalism.generation.service;

import com.windf.core.service.ManageService;
import com.windf.minimalism.generation.entity.Field;
import com.windf.minimalism.generation.model.expand.ExpandItemList;
import com.windf.minimalism.generation.model.expand.FieldExpandItem;

/**
 * 字段的服务
 */
public interface FieldService extends ManageService<Field>, ExpandItemList<FieldExpandItem> {

}
