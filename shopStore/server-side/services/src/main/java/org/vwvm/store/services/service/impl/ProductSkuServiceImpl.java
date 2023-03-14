package org.vwvm.store.services.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.vwvm.store.beans.entity.ProductSku;
import org.vwvm.store.mappers.mapper.ProductSkuMapper;
import org.vwvm.store.services.service.IProductSkuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-25
 */
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements IProductSkuService {

}
