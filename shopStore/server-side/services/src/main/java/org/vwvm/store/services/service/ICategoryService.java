package org.vwvm.store.services.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.beans.entity.ProductCategory;
import org.vwvm.store.commons.vo.ResultVO;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-15
 */
public interface ICategoryService extends IService<ProductCategory> {

    ResultVO categoryList();

    ResultVO firstLevelCategoryList();

}
