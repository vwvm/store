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

    List<CategoryBean> selectAllCategory();

}
