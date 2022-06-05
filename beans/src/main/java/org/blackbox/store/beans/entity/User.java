package org.blackbox.store.beans.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "User对象", description = "用户信息")
public class User {

    @ApiModelProperty(dataType = "int", required = false)
    private int id;
    @ApiModelProperty(dataType = "String", required = false)
    private String userName;
    @ApiModelProperty(dataType = "String", required = false)
    private String password;
    @ApiModelProperty(dataType = "String", required = false)
    private String userImg;
}
