package org.blackbox.store.commons.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "响应的ResultVO对象", description = "封装接口返回给前端的数据")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO {

    @ApiModelProperty(value = "响应给前端的状态码", dataType = "int")
    private int code;


    @ApiModelProperty(value = "响应给前端的提示信息", dataType = "String")
    private String msg;


    @ApiModelProperty(value = "响应给前端的数据", dataType = "String")
    private Object data;

}
