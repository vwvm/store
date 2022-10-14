package org.blackbox.store.services.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.entity.ProductComments;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.mappers.mapper.ProductCommentsMapper;
import org.blackbox.store.services.service.IProductCommentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品评价  服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-10-14
 */
@Service
public class ProductCommentsServiceImpl extends ServiceImpl<ProductCommentsMapper, ProductComments> implements IProductCommentsService {

    @Resource ProductCommentsMapper productCommentsMapper;

    /**
     * @param productId
     * @return
     */
    @Override
    public ResultVO productCommentsList(String productId) {
        return new ResultVO(ResStatus.OK, "success", productCommentsMapper.selectProductCommentsById(productId));


    }
}
