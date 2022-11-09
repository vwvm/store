package org.vwvm.store.services.service;

import org.vwvm.store.beans.entity.ProductComments;
import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.commons.vo.ResultVO;

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
