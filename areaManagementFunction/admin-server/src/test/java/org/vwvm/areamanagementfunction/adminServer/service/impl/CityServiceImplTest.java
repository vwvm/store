package org.vwvm.areamanagementfunction.adminServer.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.areamanagementfunction.adminServer.AdminServerApplication;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AdminServerApplication.class)
class CityServiceImplTest {

    @Resource
    private CityServiceImpl cityService;

    @Test
    void testPage() {
        cityService.page(new Page<>(1,1)).getRecords().forEach(System.out::println);
    }

}