package com.windf.minimalism.generation.service.business;

import com.windf.core.repository.ManageRepository;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.repository.ModuleRepository;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.minimalism.generation.service.ModuleService;
import com.windf.minimalism.generation.service.business.config.CodeTemplate;
import com.windf.plugin.service.business.BaseManageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleServiceImpl extends BaseManageService<Module> implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private EntityService entityService;
    private CodeTemplate codeTemplate = new CodeTemplate();

    @Override
    public ManageRepository<Module> getManageRepository() {
        return moduleRepository;
    }

    @Override
    public void commit(String moduleId) {
        // 组织数据
        Map<String, Object> data = new HashMap<>();
        Module module = this.getManageRepository().detail(moduleId);
        List<Entity> entities = entityService.getByModuleId(moduleId);
        data.put("module", module);
        data.put("entities", entities);

        // 获取目标文件和模板文件
        String templatePath = codeTemplate.getTemplatePath();
        String targetPath = codeTemplate.getTargetPath();

        // 递归遍历文件，进行解析
        analyzePath(templatePath, targetPath, data);
    }

    /**
     * 解析文件夹，把原文件中的文件，解析到目标文件中
     * @param templatePathStr
     * @param targetPathStr
     * @param model  具体的数据 TODO 不应该传递，应该设置对象传输
     */
    private void analyzePath(String templatePathStr, String targetPathStr, Object model) {
        File templatePath = new File(templatePathStr);
        if (!templatePath.isDirectory()) {  // 如果不是文件夹，直接解析文件
            analyzeFile(templatePath, new File(targetPathStr), model);
            return;
        }

        // 遍历目录的子文件
        for (File file : templatePath.listFiles()) {
            String subTemplatePathStr = templatePathStr + "/" + file.getName();
            String subTargetPathStr = targetPathStr + "/" + file.getName();
            // 递归遍历
            analyzePath(subTemplatePathStr, subTargetPathStr, model);
        }
    }

    /**
     * 解析模板，将模板文件解析为目标文件
     * @param templateFile
     * @param targetFile
     */
    private void analyzeFile(File templateFile, File targetFile, Object model) {
        if (!targetFile.exists()) {
            // 如果文件不存在，先创建
            targetFile.getParentFile().mkdirs();
        }

        Configuration cfg = this.getConfig(templateFile.getParentFile().getPath());

        try {
            Template temp = cfg.getTemplate(templateFile.getName());

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            temp.process(model, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param path
     * @return
     */
    private Configuration getConfig(String path) {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        try {
            cfg.setDirectoryForTemplateLoading(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        return cfg;
    }
}
