package org.blackbox.store.services.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.entity.ProductImg;
import org.blackbox.store.mappers.mapper.ProductImgMapper;
import org.blackbox.store.services.service.IProductImgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品图片  服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
@Service
public class ProductImgServiceImpl extends ServiceImpl<ProductImgMapper, ProductImg> implements IProductImgService {

}
