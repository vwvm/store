package org.vwvm.areamanagementfunction.adminServer.commons.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h3>store</h3>
 * <p>代码生成器</p>
 *
 * @author : BlackBox
 * {@code @date} : 2023-02-31 18:08
 **/
public class MybatisGenerator {

    public static void main(String[] args) {

        // 配置数据库
        DataSourceConfig.Builder builder1 = new DataSourceConfig
                .Builder("jdbc:mysql://localhost:43306/shopStore", "root", "root");
        generatorB(builder1);

    }

    /**
     * @param builder1
     * 默认方式
     */
    private static void generatorB(DataSourceConfig.Builder builder1) {
        FastAutoGenerator.create(builder1)
                //全局配置
                .globalConfig(builder -> builder.outputDir("F:/project/synthetical/areaManagementFunction/src/main/java")
                        .enableSwagger()        // 开启 swagger 模式
                        .author("BlackBox")
                        .disableOpenDir())
                //包配置
                .packageConfig(builder -> {
                    builder.controller("api")
                            .parent("org.shopStore"); // 设置父包名
                })
                //策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名")))
                //模板配置
//                .templateConfig(builder -> builder.disable(TemplateType.XML))
                //模板引擎
                .templateEngine(new FreemarkerTemplateEngine())
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
