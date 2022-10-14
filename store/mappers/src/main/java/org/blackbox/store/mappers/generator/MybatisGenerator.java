package org.blackbox.store.mappers.generator;

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
                .Builder("jdbc:mysql://localhost:43306/store", "root", "root");
        generatorB(builder1);

    }

    private static void generatorB(DataSourceConfig.Builder builder1) {
        FastAutoGenerator.create(builder1)
                //全局配置
                .globalConfig(builder -> builder.outputDir("F:/project/synthetical/store")
                        .author("BlackBox")
                        .disableOpenDir())
                //包配置
                .packageConfig(builder -> builder
                        .parent("store")
                        .entity("beans.src.main.java.org.blackbox.store.beans.entity")
                        .mapper("mappers.src.main.java.org.blackbox.store.mappers.mapper")
                        .xml("mappers.src.main.resources.mappers")
                        .service("services.src.main.java.org.blackbox.store.services.service")
                        .controller("api.src.main.java.org.blackbox.store.api.controller")
                        .serviceImpl("services.src.main.java.org.blackbox.store.services.service.impl"))
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


    private static void generatorA(DataSourceConfig.Builder builder1) {
//        String templatePath = "/templates/entity.java.ftl";
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);

        FastAutoGenerator.create(builder1)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
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
