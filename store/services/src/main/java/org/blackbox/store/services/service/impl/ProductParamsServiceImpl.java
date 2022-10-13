package org.blackbox.store.services.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.entity.ProductParams;
import org.blackbox.store.mappers.mapper.ProductParamsMapper;
import org.blackbox.store.services.service.IProductParamsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品参数  服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-10-13
 */
@Service
public class ProductParamsServiceImpl extends ServiceImpl<ProductParamsMapper, ProductParams> implements IProductParamsService {

}
