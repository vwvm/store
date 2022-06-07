package org.blackbox.store.beans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User对象", description = "用户信息")
@TableName("user")
public class User {

    @ApiModelProperty(dataType = "int", required = false)
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(dataType = "String", required = false)
    @TableField("username")
    private String userName;
    @ApiModelProperty(dataType = "String", required = false)
    private String password;
    @ApiModelProperty(dataType = "String", required = false)
    @TableField("img")
    private String userImg;
}
