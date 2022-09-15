package org.blackbox.store.services.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.entity.Category;
import org.blackbox.store.mappers.mapper.CategoryMapper;
import org.blackbox.store.services.service.ICategoryService;
import org.springframework.stereotype.Service;

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

}
