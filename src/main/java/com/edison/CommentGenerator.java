package com.edison;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author TSN
 * @date 2019/12/26
 */
public class CommentGenerator extends EmptyCommentGenerator {
    private final Collection<Annotations> annotations;
    private String author;
    /**
     * 当前时间
     */
    private String currentDateStr;


    public CommentGenerator() {
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        annotations = new LinkedHashSet<Annotations>(Annotations.values().length);
    }


    @Override
    public void addConfigurationProperties(Properties properties) {
        annotations.add(Annotations.DATA);
        for (String stringPropertyName : properties.stringPropertyNames()) {
            if (stringPropertyName.contains(".")) {
                continue;
            }
            if (!Boolean.parseBoolean(properties.getProperty(stringPropertyName))) {
                continue;
            }
            Annotations annotation = Annotations.getValueOf(stringPropertyName);
            if (annotation == null) {
                continue;
            }

            String optionsPrefix = stringPropertyName + ".";
            for (String propertyName : properties.stringPropertyNames()) {
                if (!propertyName.startsWith(optionsPrefix)) {
                    continue;
                }
                String propertyValue = properties.getProperty(propertyName);
                annotation.appendOptions(propertyName, propertyValue);
                annotations.add(annotation);
                annotations.addAll(Annotations.getDependencies(annotation));
            }
            annotations.add(annotation);
        }
        author = properties.getProperty("author");
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + remarks);
        topLevelClass.addJavaDocLine(" * @author   " + author);
        topLevelClass.addJavaDocLine(" * @date   " + currentDateStr);
        topLevelClass.addJavaDocLine(" */");
        addClassAnnotation(topLevelClass);
    }

    private void addClassAnnotation(TopLevelClass topLevelClass) {
        for (Annotations annotation : annotations) {
            topLevelClass.addImportedType(annotation.javaType);
            topLevelClass.addAnnotation(annotation.asAnnotation());
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        field.addJavaDocLine(" */");
    }
}

