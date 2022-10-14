package org.blackbox.store.services.service;

import org.blackbox.store.beans.entity.ProductComments;
import com.baomidou.mybatisplus.extension.service.IService;
import org.blackbox.store.commons.vo.ResultVO;

/**
 * <p>
 * 商品评价  服务类
 * </p>
 *
 * @author BlackBox
 * @since 2022-10-14
 */
public interface IProductCommentsService extends IService<ProductComments> {

    public ResultVO productCommentsList(String productId);
}
