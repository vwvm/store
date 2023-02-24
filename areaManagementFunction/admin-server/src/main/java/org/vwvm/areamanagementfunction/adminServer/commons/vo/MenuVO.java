package org.vwvm.areamanagementfunction.adminServer.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>admin-server</h3>
 * <p>
 *     临时用于设置菜单列表
 * </p>
 *
 * @author : BlackBox
 * @date : 2023-02-22 15:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO {

    public String authName;
    public String path;
    public MenuVO children;
}
