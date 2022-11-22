package org.vwvm.store.beans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author BlackBox
 * @since 2022-10-14
 */
@Data
@TableName("shopping_cart")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * skuID
     */
    private String skuId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 购物车商品数量
     */
    private String cartNum;

    /**
     * 添加购物车时间
     */
    private String cartTime;

    /**
     * 添加购物车时商品价格
     */
    private BigDecimal productPrice;

    /**
     * 选择的套餐的属性
     */
    private String skuProps;

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId = " + cartId +
                ", productId = " + productId +
                ", skuId = " + skuId +
                ", userId = " + userId +
                ", cartNum = " + cartNum +
                ", cartTime = " + cartTime +
                ", productPrice = " + productPrice +
                ", skuProps = " + skuProps +
                "}";
    }
}
