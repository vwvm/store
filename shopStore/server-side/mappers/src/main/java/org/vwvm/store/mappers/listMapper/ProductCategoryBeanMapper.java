package org.vwvm.store.mappers.listMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.vwvm.store.beans.bean.ProductCategoryBean;
import org.vwvm.store.beans.entity.ProductCategory;

import java.util.List;

/**
 * <p>
 * 商品分类信息 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2023-03-08
 */
public interface ProductCategoryBeanMapper extends BaseMapper<ProductCategoryBean> {

    List<ProductCategoryBean> selectProductCategory();
}
