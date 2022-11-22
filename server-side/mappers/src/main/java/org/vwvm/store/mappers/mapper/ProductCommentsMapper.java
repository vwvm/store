package org.vwvm.store.mappers.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.vwvm.store.beans.bean.ProductCommentsBean;
import org.vwvm.store.beans.entity.ProductComments;

import java.util.List;

/**
 * <p>
 * 商品评价  Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2022-10-14
 */
public interface ProductCommentsMapper extends BaseMapper<ProductComments> {

    public List<ProductCommentsBean> selectProductCommentsById(String productId);

}
