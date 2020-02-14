package com.windf.minimalism.generation.template.java.processor;

import com.windf.minimalism.generation.model.template.CodeFileProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportProcessor implements CodeFileProcessor {

    @Override
    public void process(List<String> lineList) {
        // 解析包名
        for (String line : lineList) {
            Pattern pattern = Pattern.compile("\\[package\\|\\|(.*)\\]");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String m = matcher.group();
                matcher.group(1);
            }
        }

    }
}
