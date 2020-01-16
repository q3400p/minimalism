package com.windf.plugin.controller.api.controller;

import com.windf.core.entity.BaseEntity;
import com.windf.core.entity.Page;
import com.windf.core.entity.ResultData;
import com.windf.core.entity.SearchData;
import com.windf.core.service.ManageService;
import com.windf.core.util.ParameterUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 基本的管理端控制类，实现增删改查
 * @param <T>
 */
@RestController
public abstract class BaseManageController<T extends BaseEntity> extends BaseController {

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
    @GetMapping("/{id}")
    public ResultData detail(@PathVariable String id) {
        ParameterUtil.assertNotEmpty(id);

        T data = this.getManageService().detail(id);

        return response().successData(data);
    }

    /**
     * 搜索
     * @param searchData    搜索信息
     * @return
     */
    @GetMapping("/")
    public ResultData search(@RequestBody SearchData searchData) {
        Page<T> data = this.getManageService().search(searchData);

        return response().successData(data);
    }

    /**
     * 创建
     * @param entity    要添加的信息
     */
    @PostMapping("/")
    public ResultData create(@RequestBody T entity) {
        ParameterUtil.assertNotEmpty(entity);

        this.getManageService().create(entity);

        return response().success();
    }

    /**
     * 修改
     * @param entity    要修改的信息
     */
    @PutMapping("/")
    public ResultData update(@RequestBody T entity) {
        ParameterUtil.assertNotEmpty(entity);

        this.getManageService().update(entity);

        return response().success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/")
    public ResultData delete(@RequestParam String ids) {
        ParameterUtil.assertNotEmpty(ids);

        List<String> idList = ParameterUtil.ids(ids);

        this.getManageService().delete(idList);

        return response().success();
    }

    /**
     * 批量创建
     * @param multipartFile 要导入的文件
     * @param fieldMap      字段对应关系，自定名，对应的实体字段
     */
    @PostMapping("/import")
    public ResultData batchImport(MultipartFile multipartFile, @RequestBody Map<String, String> fieldMap) {
        // TODO 需要做成异步式的

        ParameterUtil.assertNotEmpty(fieldMap);

        File file = this.saveFile(multipartFile, null);

        this.getManageService().batchImport(file, fieldMap);

        return response().success();
    }

    /**
     * 批量导出
     * @param searchData    搜索信息
     * @return
     */
    @GetMapping("/export")
    public ResultData export(@RequestBody SearchData searchData) {
        // TODO 需要做成异步式的
        ParameterUtil.assertNotEmpty(searchData);

        File data = this.getManageService().batchExport(searchData);

        // 返回文件会直接下载
        return response().successData(data);
    }
}
