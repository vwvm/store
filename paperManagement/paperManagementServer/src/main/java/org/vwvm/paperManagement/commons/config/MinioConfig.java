package org.vwvm.paperManagement.commons.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * description: 获取配置文件信息
 *
 * @author: weirx
 * @time: 2021/8/25 9:50
 */
@Configuration
@EnableConfigurationProperties(MinioPropertiesConfig.class)
public class MinioConfig {

    private final MinioPropertiesConfig minioPropertiesConfig;

    @Autowired
    public MinioConfig(MinioPropertiesConfig minioPropertiesConfig) {
        this.minioPropertiesConfig = minioPropertiesConfig;
    }


    /**
     * 初始化 MinIO 客户端
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioPropertiesConfig.getEndpoint())
                .credentials(minioPropertiesConfig.getAccessKey(), minioPropertiesConfig.getSecretKey())
                .build();
    }
}
