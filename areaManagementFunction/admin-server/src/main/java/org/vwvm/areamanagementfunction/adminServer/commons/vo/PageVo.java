package org.vwvm.areamanagementfunction.adminServer.commons.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <h3>admin-server</h3>
 * <p>分页信息</p>
 *
 * @author : BlackBox
 * @date : 2023-02-22 19:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "页码对象", description = "页码信息")
public class PageVo {

    @ApiModelProperty(value = "查询信息")
    Map<String, String> query = null;
    /**
     * 当前页
     */
    Integer pageNum;
    /**
     * 显示条数
     */
    Integer pageSize;
}
