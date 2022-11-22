package org.vwvm.store.beans.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Users对象", description = "用户买家信息")
public class Users {

    @ApiModelProperty(dataType = "long", required = false, value = "主键，id")
    private long userId;
    @ApiModelProperty(dataType = "String", required = true, value = "用户名")
    private String username;
    @ApiModelProperty(dataType = "String", required = true, value = "密码")
    private String password;
    @ApiModelProperty(dataType = "String", required = false, value = "昵称")
    private String nickname;
    @ApiModelProperty(dataType = "String", required = false, value = "真实姓名")
    private String realname;
    @ApiModelProperty(dataType = "String", required = false, value = "用户头像")
    private String userImg;
    @ApiModelProperty(dataType = "String", required = false, value = "手机号")
    private String userMobile;
    @ApiModelProperty(dataType = "String", required = false, value = "邮件地址")
    private String userEmail;
    @ApiModelProperty(dataType = "String", required = false, value = "性别")
    private String userSex;
    @ApiModelProperty(dataType = "Date", required = false, value = "生日")
    private Date userBirth;
    @ApiModelProperty(dataType = "Date", required = true, value = "注册时间")
    private Date userRegtime;
    @ApiModelProperty(dataType = "Date", required = true, value = "更新时间")
    private Date userModtime;


}
