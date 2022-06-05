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
@ApiModel(value = "Users对象", description = "用户信息")
public class Users {

    @ApiModelProperty(dataType = "int", required = false)
    private int userId;
    @ApiModelProperty(dataType = "String", required = true, value = "用户注册账号")
    private String username;
    @ApiModelProperty(dataType = "String", required = true, value = "用户注册密码")
    private String password;
    @ApiModelProperty(dataType = "String", required = true, value = "用户真实姓名")
    private String realName;
    @ApiModelProperty(dataType = "String", required = true, value = "用户头像url")
    private String userImg;
}
