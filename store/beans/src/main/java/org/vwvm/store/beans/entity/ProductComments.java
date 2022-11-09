package org.vwvm.store.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品评价 
 * </p>
 *
 * @author BlackBox
 * @since 2022-10-14
 */
@TableName("product_comments")
public class ProductComments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String commId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 订单项(商品快照)ID 可为空
     */
    private String orderItemId;

    /**
     * 评论用户id 用户名须脱敏
     */
    private String userId;

    /**
     * 是否匿名（1:是，0:否）
     */
    private Integer isAnonymous;

    /**
     * 评价类型（1好评，0中评，-1差评）
     */
    private Integer commType;

    /**
     * 评价等级 1：好评 2：中评 3：差评
     */
    private Integer commLevel;

    /**
     * 评价内容
     */
    private String commContent;

    /**
     * 评价晒图(JSON {img1:url1,img2:url2}  )
     */
    private String commImgs;

    /**
     * 评价时间 可为空
     */
    private LocalDateTime sepcName;

    /**
     * 是否回复（0:未回复，1:已回复）
     */
    private Integer replyStatus;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复时间
     */
    private LocalDateTime replyTime;

    /**
     * 是否显示（1:是，0:否）
     */
    private Integer isShow;

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Integer getCommType() {
        return commType;
    }

    public void setCommType(Integer commType) {
        this.commType = commType;
    }

    public Integer getCommLevel() {
        return commLevel;
    }

    public void setCommLevel(Integer commLevel) {
        this.commLevel = commLevel;
    }

    public String getCommContent() {
        return commContent;
    }

    public void setCommContent(String commContent) {
        this.commContent = commContent;
    }

    public String getCommImgs() {
        return commImgs;
    }

    public void setCommImgs(String commImgs) {
        this.commImgs = commImgs;
    }

    public LocalDateTime getSepcName() {
        return sepcName;
    }

    public void setSepcName(LocalDateTime sepcName) {
        this.sepcName = sepcName;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public LocalDateTime getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(LocalDateTime replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "ProductComments{" +
        "commId = " + commId +
        ", productId = " + productId +
        ", productName = " + productName +
        ", orderItemId = " + orderItemId +
        ", userId = " + userId +
        ", isAnonymous = " + isAnonymous +
        ", commType = " + commType +
        ", commLevel = " + commLevel +
        ", commContent = " + commContent +
        ", commImgs = " + commImgs +
        ", sepcName = " + sepcName +
        ", replyStatus = " + replyStatus +
        ", replyContent = " + replyContent +
        ", replyTime = " + replyTime +
        ", isShow = " + isShow +
        "}";
    }
}
