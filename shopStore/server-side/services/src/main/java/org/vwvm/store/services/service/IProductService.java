package org.vwvm.store.services.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.vwvm.store.beans.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.beans.entity.ProductSpecification;
import org.vwvm.store.commons.vo.ResultVO;

import java.util.List;

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

    ResultVO selectCategoryProducts(Integer id);
    List<Product> productSearch(String str);

    List<Product> productSearch(String str, String type, Integer limit);

    Page<Product> productSearchPage(String str, String type, Integer pageNum, Integer pageSize);
    List<Product> productSearchType(String type);

    Product productId(Integer productId);
}
