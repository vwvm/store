package org.vwvm.areamanagementfunction.adminServer.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.areamanagementfunction.adminServer.AdminServerApplication;
import org.vwvm.areamanagementfunction.adminServer.entity.Region;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AdminServerApplication.class)
class RegionServiceImplTest {

    @Resource
    private RegionServiceImpl regionService;

    @Test
    void testJson(){
        List<Region> regionList = regionService.list();
        regionList.forEach(region -> {
            for (String address : region.getAddress()) {
                System.out.println("address = " + address);
            }
        });
    }
}