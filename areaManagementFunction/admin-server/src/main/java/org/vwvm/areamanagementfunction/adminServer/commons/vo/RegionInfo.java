package org.vwvm.areamanagementfunction.adminServer.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h3>admin-server</h3>
 * <p>页面搜索用</p>
 *
 * @author : BlackBox
 * @date : 2023-02-23 08:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionInfo {
    private String address;

    private String regionCity;

    private String regionName;

    private Date creationTimeA;

    private Date creationTimeB;
}
