package org.vwvm.store.services.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.vwvm.store.beans.entity.ProductCategory;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.mappers.mapper.CategoryMapper;
import org.vwvm.store.services.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, ProductCategory> implements ICategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * @return ResultVO
     * 查询分类列表，包含子分类
     */
    @Override
    public ResultVO categoryList() {
        List<ProductCategory> categoryBeanList = categoryMapper.selectAllCategory();
        return new ResultVO(ResStatus.OK, "success", categoryBeanList);

    }

    /**
     * @return ResultVO
     * 查询1级分类列表，当前分类下销量前6个商品
     */
    @Override
    public ResultVO firstLevelCategoryList() {
        List<ProductCategory> categoryBeanList = categoryMapper.selectFirstLevelCategory();
        return new ResultVO(ResStatus.OK, "success", categoryBeanList);
    }
}
