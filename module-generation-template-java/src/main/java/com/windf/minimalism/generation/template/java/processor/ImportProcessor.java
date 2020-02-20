package com.windf.minimalism.generation.template.java.processor;

import com.windf.minimalism.generation.model.template.CodeFileProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportProcessor implements CodeFileProcessor {

    @Override
    public void process(List<String> lineList) {
        Set<String> packages = new HashSet<>();

        // 解析包名
        for (int i = 0; i < lineList.size(); i++) {
            String line = lineList.get(i);

            Pattern pattern = Pattern.compile("\\[package\\|\\|([^\\|]*)\\|\\|([^\\]]*)\\]");
            Matcher matcher = pattern.matcher(line);

            List<String[]> set = new ArrayList<>();

            while (matcher.find()) {
                String importId = matcher.group(1);
                String classCode = matcher.group(2);

                set.add(new String[]{matcher.group(), classCode});

                packages.add(importId);
            }

            for (String[] ss : set) {
                line = line.replace(ss[0], ss[1]);
            }

            lineList.set(i, line);
        }

        if (packages.size() > 0) {
            for (String s : packages) {
                // 不导入空
                if ("void".equalsIgnoreCase(s)) {
                    continue;
                }

                // 不导入java.lang
                if (s.startsWith("java.lang.") && s.split("\\.").length == 3) {
                    continue;
                }

                lineList.add(1, "import " + s + ";");
            }

            lineList.add(1, "");
        }

    }
}
