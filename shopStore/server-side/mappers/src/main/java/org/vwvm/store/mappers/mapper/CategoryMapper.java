package org.vwvm.store.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.vwvm.store.beans.entity.ProductCategory;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-15
 */
@Repository
public interface CategoryMapper extends BaseMapper<ProductCategory> {

    /**
     * @return 查询集合
     * 使用连接查询
     */
    List<ProductCategory> selectAllCategory();

    /**
     * @return 查询集合
     * 使用子查询
     */
    List<ProductCategory> selectAllCategoryB(int parentId);


    /**
     * @return 查询集合
     * 查询一级类别
     */
    List<ProductCategory> selectFirstLevelCategory();
}
