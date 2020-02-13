package com.windf.minimalism.generation.template.java;

import com.windf.core.exception.CodeException;
import com.windf.core.util.CollectionUtil;
import com.windf.core.util.FileUtil;
import com.windf.core.util.StringUtil;
import com.windf.minimalism.generation.model.expand.ExpandItem;
import com.windf.minimalism.generation.model.template.CodeTemplateHandler;
import com.windf.minimalism.generation.template.java.expand.ClassCode;
import com.windf.minimalism.generation.template.java.expand.ClassId;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java的代码生成的处理程序
 */
public abstract class JavaCodeTemplateHandler implements CodeTemplateHandler {
    @Override
    public List<ExpandItem> getExpandItems() {
        List<ExpandItem> result = new ArrayList<>();
        result.add(new ClassCode());
        result.add(new ClassId());
        return result;
    }

    @Override
    public void processFile(File file) {
        try {
            // 读取文件
            List<String> lineList = FileUtil.readLine(file);

            // 解析多个空行
            boolean frontLineIsEmpty = false;
            Iterator<String> iterator = lineList.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();

                // 寻找空行
                if (StringUtil.isBlank(line)) {
                    // 如果上一个还是空行，不要这一行了
                    if (frontLineIsEmpty) {
                        iterator.remove();
                    } else {
                        frontLineIsEmpty = true;
                    }
                } else {
                    // 如果发现非空行，标记前一个为空行
                    frontLineIsEmpty = false;
                }
            }

            // 解析包名
            String lines = StringUtil.join(lineList, "\r\n");
            Pattern pattern = Pattern.compile("\\[package\\|\\|(.*)\\]");
            Matcher matcher = pattern.matcher(lines);
            while (matcher.find()) {
                String m = matcher.group();
                matcher.group(1);
            }

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                byte[] bytesArray = lines.getBytes();
                fos.write(bytesArray);
                fos.flush();
            } catch (IOException e) {
                throw new CodeException(e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        throw new CodeException(e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new CodeException(e);
        }
    }

}
