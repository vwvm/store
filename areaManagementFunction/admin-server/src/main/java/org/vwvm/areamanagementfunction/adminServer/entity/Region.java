package org.vwvm.areamanagementfunction.adminServer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Region对象", description = "")
@TableName(value = "region", autoResultMap = true)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("区域名称")
    private String regionName;

    @ApiModelProperty("区域城市")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> regionalCity;

    @ApiModelProperty("地址")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> address;

    @ApiModelProperty("地址的大小")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> addressSize;

    @ApiModelProperty("创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标记")
    private Boolean deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Region{" +
            "id = " + id +
            ", regionName = " + regionName +
            ", regionalCity = " + regionalCity +
            ", address = " + address +
            ", creationTime = " + creationTime +
            ", updateTime = " + updateTime +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
