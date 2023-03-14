package org.vwvm.store.beans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author BlackBox
 * @since 2023-03-07
 */
@TableName("index_img")
@ApiModel(value = "IndexImg对象", description = "轮播图")
public class IndexImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("背景颜色")
    private String imgBgColor;

    @ApiModelProperty("商品id")
    private String productId;

    @ApiModelProperty("商品分类id")
    private String categoryId;

    @ApiModelProperty("轮播图展示顺序")
    private Integer seq;

    @ApiModelProperty("轮播图类型")
    private Integer indexType;

    @ApiModelProperty("是否展示")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty("创建人id")
    private Integer creationUserId;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgBgColor() {
        return imgBgColor;
    }

    public void setImgBgColor(String imgBgColor) {
        this.imgBgColor = imgBgColor;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getIndexType() {
        return indexType;
    }

    public void setIndexType(Integer indexType) {
        this.indexType = indexType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(Integer creationUserId) {
        this.creationUserId = creationUserId;
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
        return "IndexImg{" +
            "id = " + id +
            ", imgUrl = " + imgUrl +
            ", imgBgColor = " + imgBgColor +
            ", productId = " + productId +
            ", categoryId = " + categoryId +
            ", seq = " + seq +
            ", indexType = " + indexType +
            ", status = " + status +
            ", creationTime = " + creationTime +
            ", creationUserId = " + creationUserId +
            ", updateTime = " + updateTime +
            ", updateUserId = " + updateUserId +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
