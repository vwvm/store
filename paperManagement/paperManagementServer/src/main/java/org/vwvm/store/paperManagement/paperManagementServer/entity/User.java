package org.vwvm.store.paperManagement.paperManagementServer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用于记录系统的所有用户
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@ApiModel(value = "User对象", description = "用于记录系统的所有用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名，可用于登录")
    private String userUsername;

    @ApiModelProperty("用户密码，可用于登录")
    private String userPassword;

    @ApiModelProperty("用户电话，可用于登录")
    private String userTelephone;

    @ApiModelProperty("用户邮箱，可用于登录")
    private String userEmail;

    @ApiModelProperty("用户角色，0系统管理，1访客，其他自定义")
    private Integer userRoles;

    @ApiModelProperty("创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private Integer updateUserId;

    @ApiModelProperty("删除标记")
    private Boolean deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Integer userRoles) {
        this.userRoles = userRoles;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "User{" +
            "id = " + id +
            ", userUsername = " + userUsername +
            ", userPassword = " + userPassword +
            ", userTelephone = " + userTelephone +
            ", userEmail = " + userEmail +
            ", userRoles = " + userRoles +
            ", creationTime = " + creationTime +
            ", updateTime = " + updateTime +
            ", updateUserId = " + updateUserId +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
