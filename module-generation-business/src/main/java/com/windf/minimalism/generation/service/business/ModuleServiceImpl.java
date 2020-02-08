package com.windf.minimalism.generation.service.business;

import com.windf.core.Constant;
import com.windf.core.repository.ManageRepository;
import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.entity.Entity;
import com.windf.minimalism.generation.entity.Module;
import com.windf.minimalism.generation.model.template.CodeTemplateHandler;
import com.windf.minimalism.generation.model.template.CodeTemplateHandlerProcess;
import com.windf.minimalism.generation.repository.ModuleRepository;
import com.windf.minimalism.generation.service.EntityService;
import com.windf.minimalism.generation.service.ModuleService;
import com.windf.minimalism.generation.service.business.config.CodeTemplate;
import com.windf.minimalism.generation.service.business.model.ResourceFile;
import com.windf.plugin.service.business.BaseManageService;
import freemarker.cache.StringTemplateLoader;
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
    private static final String DEFAULT_TEMPLATE_NAME = "default";

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
        Module module = this.getManageRepository().detail(moduleId);
        List<Entity> entities = entityService.getByModuleId(moduleId);

        for (CodeTemplateHandler handler : CodeTemplateHandlerProcess.getInstance().getAllCodeTemplateHandler()) {

            // 组织数据
            Map<String, Object> data = new HashMap<>();
            // TODO 添加深度复制，防止污染元数据
            // TODO 添加属性覆盖方法，使用每个模块自己的属性（属性可以继承原始属性）
            data.put("module", handler.processModule(module));
            data.put("entities", entities);

            // 获取目标文件和模板文件
            String templatePath = handler.getTemplatePath();
            String targetPath = codeTemplate.getTargetPath();

            // 递归遍历文件，进行解析
            analyzePath(templatePath, targetPath, data, handler);
        }
    }

    /**
     * 解析文件夹，把原文件中的文件，解析到目标文件中
     * @param templatePathStr
     * @param targetPathStr
     * @param model  具体的数据 TODO 不应该传递，应该设置对象传输
     * @param handler
     */
    private void analyzePath(String templatePathStr, String targetPathStr, Map<String, Object> model, CodeTemplateHandler handler) {
        // 如果是描述文件，直接返回
        if (templatePathStr.endsWith(codeTemplate.getDefineFileExt())) {
            return;
        }

        ResourceFile templateResource = new ResourceFile(templatePathStr, handler.getClass());
        if (!templateResource.isDirectory()) {  // 如果不是文件夹，直接解析文件
            analyzeFile(templatePathStr, targetPathStr, model, handler);
            return;
        }

        // 遍历目录的子文件
        for (String path : templateResource.listPath()) {
            String subTemplatePathStr = templatePathStr + "/" + path;
            String subTargetPathStr = targetPathStr + "/" + path;
            // 递归遍历
            analyzePath(subTemplatePathStr, subTargetPathStr, model, handler);
        }
    }

    /**
     * 解析模板，将模板文件解析为目标文件
     * @param templatePath
     * @param targetFileStr 目标文件路径，中间可能会有变量
     * @param handler
     */
    private void analyzeFile(String templatePath, String targetFileStr, Map<String, Object> model, CodeTemplateHandler handler) {
        // 如果有描述文件，按照描述文件的返回进行循环实体
        ResourceFile defineFile = new ResourceFile(templatePath + codeTemplate.getDefineFileExt(), handler.getClass());

        if (defineFile.exists()) {
            String defineString = analyzePathValue(defineFile.readFile(), model);
            String[] entitiesIds = defineString.trim().split("\n");
            // 循环所有实体
            for (String entityId : entitiesIds) {
                // TODO 需要设置常量到配置文件中
                Map<String, Object> entityMap = new HashMap<>();
                entityMap.putAll(model);
                Entity entity = entityService.detail(entityId.trim());
                entityMap.put("entity", handler.processEntity(entity));
                analyzeFileAndCopy(templatePath, targetFileStr, entityMap, handler);
            }
        } else {
            // 如果没有描述文件，直接解析
            analyzeFileAndCopy(templatePath, targetFileStr, model, handler);
        }

    }

    /**
     * 解析文件并且复制到目标文件
     * @param templatePath
     * @param targetFileStr
     * @param model
     */
    private void analyzeFileAndCopy(String templatePath, String targetFileStr, Map<String, Object> model, CodeTemplateHandler handler) {
        // 解析目标路径
        targetFileStr = this.analyzePathValue(targetFileStr, model);
        File targetFile = new File(targetFileStr);

        // 创建目标文件的目录
        if (!targetFile.getParentFile().exists()) {
            // 如果文件不存在，先创建
            targetFile.getParentFile().mkdirs();
        }

        ResourceFile templateFile = new ResourceFile(templatePath, handler.getClass());
        Configuration cfg = this.getConfig(templateFile.getParentPath());

        Writer out = null;
        try {
            Template temp = cfg.getTemplate(templateFile.getName());

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            temp.process(model, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析路径中的变量，转换为真实的targetPath
     * @param targetPath
     * @param model
     * @return
     */
    private String analyzePathValue(String targetPath, Object model) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(DEFAULT_TEMPLATE_NAME, targetPath);
        cfg.setTemplateLoader(templateLoader);
        cfg.setDefaultEncoding(Constant.DEFAULT_ENCODING);

        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate(DEFAULT_TEMPLATE_NAME);
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String path = writer.toString();
        if (StringUtil.isNotEmpty(path)) {  // 恢复路径标记
            path = path.replace(":", "/");
        }

        return path;
    }

    /**
     * 获取配置
     * @param path
     * @return
     */
    private Configuration getConfig(String path) {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setClassForTemplateLoading(ModuleServiceImpl.class, path);
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        return cfg;
    }
}
