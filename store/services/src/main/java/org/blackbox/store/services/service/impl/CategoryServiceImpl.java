package org.blackbox.store.services.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.bean.CategoryBean;
import org.blackbox.store.beans.entity.Category;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.mappers.mapper.CategoryMapper;
import org.blackbox.store.services.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

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
        List<CategoryBean> categoryBeanList = categoryMapper.selectAllCategory();
        return new ResultVO(ResStatus.OK, "success", categoryBeanList);

    }

    /**
     * @return ResultVO
     * 查询1级分类列表，当前分类下销量前6商品
     */
    @Override
    public ResultVO firstLevelCategoryList() {
        List<CategoryBean> categoryBeanList = categoryMapper.selectFirstLevelCategory();
        return new ResultVO(ResStatus.OK, "success", categoryBeanList);
    }
}
