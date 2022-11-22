package org.vwvm.store.beans.adminBean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author vwvm
 * @since 2022-09-07
 */
@TableName("Member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @TableId(value = "UserId", type = IdType.AUTO)
    private Long userId;

    /**
     * 账号
     */
    @TableField("UserName")
    private String userName;

    /**
     * 密码
     */
    @TableField("UserPass")
    private String userPass;

    /**
     * 昵称
     */
    @TableField("NickName")
    private String nickName;

    /**
     * 邮件
     */
    @TableField("Email")
    private String email;

    /**
     * 手机
     */
    @TableField("Mobile")
    private String mobile;

    /**
     * myId
     */
    @TableField("MyId")
    private String myId;

    /**
     * myIdKey
     */
    @TableField("MyIdKey")
    private String myIdKey;

    /**
     * 注册IP
     */
    @TableField("RegIp")
    private String regIp;

    /**
     * 注册时间
     */
    @TableField("RegDate")
    private LocalDateTime regDate;

    /**
     * 最后登录IP
     */
    @TableField("LastLoginIp")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @TableField("LastLoginTime")
    private LocalDateTime lastLoginTime;

    /**
     * salt
     */
    @TableField("Salt")
    private String salt;

    /**
     * secques
     */
    @TableField("Secques")
    private String secques;

    /**
     * 状态
     */
    @TableField("Status")
    private String status;

    /**
     * 备注
     */
    @TableField("Remark")
    private String remark;

    /**
     * 排序
     */
    @TableField("SortNum")
    private Long sortNum;

    /**
     * 是否删除
     */
    @TableField("IsDeleted")
    private Long isDeleted;

    /**
     * 创建用户
     */
    @TableField("CreateBy")
    private Long createBy;

    /**
     * 更新用户
     */
    @TableField("UpdateBy")
    private Long updateBy;

    /**
     * 创建时间
     */
    @TableField("CreateOn")
    private LocalDateTime createOn;

    /**
     * 更新时间
     */
    @TableField("UpdateOn")
    private Date updateOn;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getMyIdKey() {
        return myIdKey;
    }

    public void setMyIdKey(String myIdKey) {
        this.myIdKey = myIdKey;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSecques() {
        return secques;
    }

    public void setSecques(String secques) {
        this.secques = secques;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSortNum() {
        return sortNum;
    }

    public void setSortNum(Long sortNum) {
        this.sortNum = sortNum;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDateTime createOn) {
        this.createOn = createOn;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    @Override
    public String toString() {
        return "Member{" + "userId = " + userId + ", userName = " + userName + ", userPass = " + userPass + ", nickName = " + nickName + ", email = " + email + ", mobile = " + mobile + ", myId = " + myId + ", myIdKey = " + myIdKey + ", regIp = " + regIp + ", regDate = " + regDate + ", lastLoginIp = " + lastLoginIp + ", lastLoginTime = " + lastLoginTime + ", salt = " + salt + ", secques = " + secques + ", status = " + status + ", remark = " + remark + ", sortNum = " + sortNum + ", isDeleted = " + isDeleted + ", createBy = " + createBy + ", updateBy = " + updateBy + ", createOn = " + createOn + ", updateOn = " + updateOn + "}";
    }
}
