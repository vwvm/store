package org.blackbox.store.services.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.blackbox.store.beans.entity.Category;
import org.blackbox.store.commons.vo.ResultVO;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-15
 */
public interface ICategoryService extends IService<Category> {

    public ResultVO categoryList();

}
