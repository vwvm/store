package org.blackbox.store.services.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.entity.ShoppingCart;
import org.blackbox.store.mappers.mapper.ShoppingCartMapper;
import org.blackbox.store.services.service.IShoppingCartService;
import org.springframework.stereotype.Service;

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

}
