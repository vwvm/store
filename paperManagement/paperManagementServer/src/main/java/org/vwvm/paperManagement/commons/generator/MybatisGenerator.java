package org.vwvm.paperManagement.commons.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
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
                .Builder("jdbc:mysql://localhost:43306/paperManagement", "root", "root");
        generatorB(builder1);

    }

    /**
     * @param builder1 默认方式
     */
    private static void generatorB(DataSourceConfig.Builder builder1) {
        FastAutoGenerator.create(builder1)
                //全局配置
                .globalConfig(builder -> builder.outputDir("F:/project/synthetical/store/paperManagement/paperManagementServer/src/main/java")
                        .enableSwagger()        // 开启 swagger 模式
                        .author("BlackBox")
                        .disableOpenDir())
                //包配置
                .packageConfig(builder -> {
                    builder.controller("api")
                            .parent("org.vwvm.paperManagement"); // 设置父包名
                })
                //策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名逗号分隔")))
                //模板配置
                .templateConfig(builder -> builder.disable(TemplateType.XML))
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
