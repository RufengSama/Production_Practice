package com.buct.spider;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String url = "jdbc:mysql://127.0.0.1:3306/acm?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true";
        FastAutoGenerator.create(url, "root", "123456")
                .globalConfig(builder -> {
                    builder.author("BUCT") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.buct") // 设置父包名
                            .moduleName("spider") // 设置父包模块名
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.impl")
                            //.controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath+"/src/main/java/com/buct/spider/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("codeforces")
                            .entityBuilder()
                            .enableLombok()
                            .idType(IdType.INPUT)
                           // .controllerBuilder()
                           // .enableRestStyle()
                            .mapperBuilder()
                            .enableMapperAnnotation();

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
