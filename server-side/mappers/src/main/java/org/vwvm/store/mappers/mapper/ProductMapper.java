package org.vwvm.store.mappers.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.vwvm.store.beans.bean.ProductBean;
import org.vwvm.store.beans.entity.Product;

import java.util.List;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * @return 商品列表
     * 商品推荐前3
     */
    List<ProductBean> selectRecommendProducts();

    /**
     * @return 商品列表
     * 查询指定类别id下的销量最高的6个商品
     */
    List<ProductBean> selectCategoryProducts(int id);
}
