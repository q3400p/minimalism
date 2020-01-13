package com.whaty.cbs.plugins.web.controller;

import com.windf.core.entity.BaseEntity;
import com.windf.core.entity.Page;
import com.windf.core.entity.ResultData;
import com.windf.core.entity.SearchData;
import com.windf.core.service.ManageService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 基本的管理端控制类，实现增删改查
 * @param <T>
 */
public abstract class ManageController<T extends BaseEntity> extends BaseController {

    /**
     * 获得管理端的service
     * @return
     */
    public abstract ManageService<T> getManageService();

    /**
     * 查看详情
     * @param id    根据id查询
     * @return
     */
    @GetMapping("/detail")
    public ResultData detail(String id) {
        T data = this.getManageService().detail(id);
        return response().successData(data);
    }

    /**
     * 搜索
     * @param searchData    搜索信息
     * @return
     */
    @GetMapping("/search")
    public ResultData search(SearchData searchData) {
        Page<T> data = this.getManageService().search(searchData);
        return response().successData(data);
    }
    /**
     * 创建
     * @param entity    要添加的信息
     */
    @GetMapping("/")
    public ResultData create(T entity) {
        this.getManageService().create(entity);
        return response().success();
    }

    /**
     * 修改
     * @param entity    要修改的信息
     */
    @PostMapping("/update")
    public ResultData update(T entity) {
        this.getManageService().update(entity);
        return response().success();
    }

    /**
     * 删除
     * @param ids   更具id进行搜索
     */
    @DeleteMapping("")
    public ResultData delete(List<String> ids) {
        this.getManageService().delete(ids);
        return response().success();
    }

    /**
     * 批量创建
     * @param file      要导入的文件
     * @param fieldMap  字段对应关系，自定名，对应的实体字段
     */
    @PostMapping("/import")
    public ResultData batchImport(File file, Map<String, String> fieldMap) {
        this.getManageService().batchImport(file, fieldMap);
        return response().success();
    }

    /**
     * 批量导出
     * @param searchData    搜索信息
     * @return
     */
    @GetMapping("/export")
    public ResultData export(SearchData searchData) {
        // TODO 获取文件下载
        File data = this.getManageService().batchExport(searchData);
        return response().successData(data);
    }
}
