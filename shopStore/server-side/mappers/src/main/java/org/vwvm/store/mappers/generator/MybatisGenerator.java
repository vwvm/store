package org.vwvm.store.mappers.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.*;

/**
 * <h3>store</h3>
 * <p>代码生成器</p>
 *
 * @author : BlackBox
 * {@code @date} : 2022-06-15 20:08
 **/
public class MybatisGenerator {

    public static void main(String[] args) {

        DataSourceConfig.Builder builder1 = new DataSourceConfig
                .Builder("jdbc:mysql://localhost:43306/shopStore", "root", "root");
        generatorB(builder1);

    }

    private static void generatorB(DataSourceConfig.Builder builder1) {
        FastAutoGenerator.create(builder1)
                //全局配置
                .globalConfig(builder -> builder.outputDir("F:/project/synthetical/store/shopStore")
                        .author("BlackBox")
                        .enableSwagger()//开启swagger
                        .disableOpenDir())
                //包配置
                .packageConfig(builder -> builder
                        .parent("server-side")
                        .entity("beans.src.main.java.org.vwvm.store.beans.entity")
                        .mapper("mappers.src.main.java.org.vwvm.store.mappers.mapper")
                        .xml("mappers.src.main.resources.mappers")
                        .service("services.src.main.java.org.vwvm.store.services.service")
                        .controller("api.src.main.java.org.vwvm.store.api.controller")
                        .serviceImpl("services.src.main.java.org.vwvm.store.services.service.impl"))
                //策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名")))
                //模板配置
//                .templateConfig(builder -> builder.disable(TemplateType.XML))
                //模板引擎
//                .templateEngine(new FreemarkerTemplateEngine())
//                .injectionConfig(consumer -> {
//                    Map<String, String> customFile = new HashMap<>();
//                    // DTO
//                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
//                    consumer.customFile(customFile);
//                })
                //执行
                .execute();
    }


    /**
     * 处理 all 情况
     *
     * @param tables
     * @return
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
