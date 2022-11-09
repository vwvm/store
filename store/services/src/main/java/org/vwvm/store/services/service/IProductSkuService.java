package org.vwvm.store.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.beans.entity.ProductSku;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-25
 */
public interface IProductSkuService extends IService<ProductSku> {

}
