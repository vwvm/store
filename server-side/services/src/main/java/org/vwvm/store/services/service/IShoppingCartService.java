package org.vwvm.store.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.beans.entity.ShoppingCart;
import org.vwvm.store.commons.vo.ResultVO;

/**
 * <p>
 * 购物车  服务类
 * </p>
 *
 * @author BlackBox
 * @since 2022-08-23
 */
public interface IShoppingCartService extends IService<ShoppingCart> {

    public ResultVO addShoppingCart(ShoppingCart shoppingCart);

}
