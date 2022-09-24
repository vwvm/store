package org.blackbox.store.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.blackbox.store.beans.entity.ProductImg;

import java.util.List;

/**
 * <p>
 * 商品图片  Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
public interface ProductImgMapper extends BaseMapper<ProductImg> {

    List<ProductImg> selectAllProductImg(int productId);
}
