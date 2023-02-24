package org.vwvm.areamanagementfunction.adminServer.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.areamanagementfunction.adminServer.AdminServerApplication;
import org.vwvm.areamanagementfunction.adminServer.service.IRegionService;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AdminServerApplication.class)
class RegionMapperTest {

    @Resource
    private RegionMapper regionMapper;

    @Test
    void testJson(){
    }
}