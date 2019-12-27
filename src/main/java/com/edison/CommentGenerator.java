package com.edison;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author TSN
 * @date 2019/12/26
 * @Description
 */
public class CommentGenerator extends EmptyCommentGenerator {
    private Properties properties;
    /**
     * 当前时间
     */
    private String currentDateStr;


    public CommentGenerator() {
        properties = new Properties();
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.TableName;");
        topLevelClass.addAnnotation("@Builder(toBuilder = true)");
        topLevelClass.addAnnotation("@TableName(\"" + introspectedTable.getFullyQualifiedTable() + "\")");

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + remarks);
        topLevelClass.addJavaDocLine(" * @author   " + properties.getProperty("author"));
        topLevelClass.addJavaDocLine(" * @date   " + currentDateStr);
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        field.addJavaDocLine(" */");
    }
}

