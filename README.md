# mybatis-generator-comment-plugin 
[![package](https://github.com/supergaga/generator/workflows/package/badge.svg)](https://github.com/supergaga/generator/actions)

A plugin for [MyBatis Generator](http://mybatis.github.io/generator/)
to use [Lombok](http://projectlombok.org/) annotations and table annotations

reference [mybatis-generator-lombok-plugin](https://github.com/softwareloop/mybatis-generator-lombok-plugin)

数据库建表语句
```mysql
CREATE TABLE `user` (
  `id` bigint(20) DEFAULT NULL COMMENT '自增id',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `mobile` bigint(255) DEFAULT NULL COMMENT '手机号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
```

默认生成的实体类
```java
package entity;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.mobile
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    private Long mobile;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.mobile
     *
     * @return the value of user.mobile
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    public Long getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.mobile
     *
     * @param mobile the value for user.mobile
     *
     * @mbg.generated Sun Feb 02 14:17:08 CST 2020
     */
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }
}
```
使用插件后 生成
```java
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @author   tsn
 * @date   2020-02-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private Long mobile;
}
```


## Using the plugin
pom 文件中引入
```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>${mybatis.generator.version}</version>
    <configuration>
        <overwrite>true</overwrite>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>com.github.supergaga</groupId>
            <artifactId>generator</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</plugin>
```
mybaitis generator 的config中 配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <context id="example"
             targetRuntime="MyBatis3Simple"
             defaultModelType="flat">

         <commentGenerator type="com.edison.CommentGenerator">
                    <property name="author" value="tsn"/>
                    <property name="allArgsConstructor" value="false"/>
                    <property name="noArgsConstructor" value="false"/>
                    <property name="toString" value="false"/>
                    <property name="builder.toBuilder" value="true"/>
                    <property name="builder" value="true"/>
         </commentGenerator>
    </context>
</generatorConfiguration>
```

如果使用github的仓库 可以参照[这里](https://help.github.com/en/github/managing-packages-with-github-packages/configuring-apache-maven-for-use-with-github-packages)配置，
项目中的引用地址使用任意一个[package](https://github.com/supergaga/generator/packages) 都行

## Authors

Maintainer:
* [supergaga](https://github.com/supergaga)
