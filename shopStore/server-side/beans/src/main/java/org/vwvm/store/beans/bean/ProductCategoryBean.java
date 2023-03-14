package org.vwvm.store.beans.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 商品分类信息
 * </p>
 *
 * @author BlackBox
 * @since 2023-03-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_category")
@ApiModel(value = "ProductCategory对象", description = "商品分类信息")
public class ProductCategoryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("主键 分类id主键")
    private Integer categoryId;

    @ApiModelProperty("分类名称 分类名称")
    private String categoryName;

    @ApiModelProperty("分类层级分类得类型， 1:一级大分类 2:二级分类 3:三级小分类")
    private Integer categoryLevel;

    @ApiModelProperty("父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级")
    private Integer parentId;

    @ApiModelProperty("图标 logo")
    private String categoryIcon;

    @ApiModelProperty("口号")
    private String categorySlogan;

    @ApiModelProperty("分类图")
    private String categoryPic;

    @ApiModelProperty("背景颜色")
    private String categoryBgColor;

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

    private List<ProductCategoryBean> productCategoryBeans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategorySlogan() {
        return categorySlogan;
    }

    public void setCategorySlogan(String categorySlogan) {
        this.categorySlogan = categorySlogan;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }

    public String getCategoryBgColor() {
        return categoryBgColor;
    }

    public void setCategoryBgColor(String categoryBgColor) {
        this.categoryBgColor = categoryBgColor;
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
        return "ProductCategoryBean{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryLevel=" + categoryLevel +
                ", parentId=" + parentId +
                ", categoryIcon='" + categoryIcon + '\'' +
                ", categorySlogan='" + categorySlogan + '\'' +
                ", categoryPic='" + categoryPic + '\'' +
                ", categoryBgColor='" + categoryBgColor + '\'' +
                ", creationTime=" + creationTime +
                ", creationUserId=" + creationUserId +
                ", updateTime=" + updateTime +
                ", updateUserId=" + updateUserId +
                ", deleteFlag=" + deleteFlag +
                ", productCategoryBeans=" + productCategoryBeans +
                '}';
    }
}
