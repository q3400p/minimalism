package com.windf.minimalism.generation.service.business.model;

import com.windf.core.exception.CodeException;
import com.windf.core.util.FileUtil;
import com.windf.core.util.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.List;

/**
 * 资源文件的处理
 * TODO 还想改用jar就不好用了，需要做成两个实现类
 */
public class ResourceFile {
    private String path;
    private File file;

    public ResourceFile(String path, Class clazz) {
        this.path = path;

        ProtectionDomain pd = clazz.getProtectionDomain();
        CodeSource cs = pd.getCodeSource();
        String basePath = cs.getLocation().getPath();

        file = new File(basePath + "/" + path);
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public List<String> listPath() {
        return Arrays.asList(file.list());
    }

    public boolean exists() {
        return file.exists();
    }

    public String readFile() {
        try {
            List<String> lines = FileUtil.readLine(file);
            return StringUtil.join(lines.toArray(), "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new CodeException(e);
        }
    }

    public String getParentPath() {
        return path.substring(0, path.lastIndexOf("/"));
    }

    public String getName() {
        return file.getName();
    }
}
