package org.blackbox.store.services.service;

import org.blackbox.store.beans.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.blackbox.store.commons.vo.ResultVO;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
public interface IProductService extends IService<Product> {

    ResultVO recommendProductsList();

    ResultVO getProductBasicInfo(String productId);

    ResultVO getProductParamsById(String productId);

}
