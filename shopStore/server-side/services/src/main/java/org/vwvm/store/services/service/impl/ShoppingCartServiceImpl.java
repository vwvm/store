package org.vwvm.store.services.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.vwvm.store.beans.entity.ShoppingCart;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.mappers.mapper.ShoppingCartMapper;
import org.vwvm.store.services.service.IShoppingCartService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 购物车  服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-08-23
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {

    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * @param shoppingCart
     * @return
     */
    @Override
    public ResultVO addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setCartTime(simpleDateFormat.format(new Date()));
        int i = shoppingCartMapper.insert(shoppingCart);
        if(i > 0){
            return new ResultVO(ResStatus.OK, "success", null);
        }
        return new ResultVO(ResStatus.NO, "final", null);
    }
}
