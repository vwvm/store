package org.vwvm.areamanagementfunction.adminServer.commons.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>admin-server</h3>
 * <p></p>
 *
 * @author : BlackBox
 * @date : 2023-02-22 10:59
 **/
@ApiModel(value = "响应的对象", description = "封装接口返回给前端的数据")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    @ApiModelProperty(value = "响应给前端的状态码", dataType = "int")
    private int status;


    @ApiModelProperty(value = "响应给前端的msg", dataType = "String")
    private Object msg;


    @ApiModelProperty(value = "响应给前端的数据", dataType = "String")
    private Object data;


}
