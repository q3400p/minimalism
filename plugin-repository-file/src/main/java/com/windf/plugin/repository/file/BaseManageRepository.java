package com.windf.plugin.repository.file;

import com.windf.core.entity.BaseEntity;
import com.windf.core.repository.ManageRepository;
import com.windf.plugin.repository.file.config.RepositoryConfig;
import com.windf.plugin.repository.file.util.JSONRepositoryUtil;

public abstract class BaseManageRepository<T extends BaseEntity> implements ManageRepository<T> {

    /**
     * 保存json文件
     * @param relativePath  相对路径
     * @param object        要保存的对象
     */
    protected void saveJsonFile(String relativePath, Object object) {
        JSONRepositoryUtil.saveJsonFile(this.getHomePath() + "/" + relativePath, object);
    }

    /**
     * 获取文件保存的路径
     * @return
     */
    protected String getHomePath() {
        return new RepositoryConfig().getHomePath();
    }
}