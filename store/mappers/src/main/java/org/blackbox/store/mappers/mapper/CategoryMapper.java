package org.blackbox.store.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.blackbox.store.beans.bean.CategoryBean;
import org.blackbox.store.beans.entity.Category;
import org.springframework.stereotype.Repository;

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
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * @return 查询集合
     * 使用连接查询
     */
    List<CategoryBean> selectAllCategory();

    /**
     * @return 查询集合
     * 使用子查询
     */
    List<CategoryBean> selectAllCategoryB(int parentId);

}
