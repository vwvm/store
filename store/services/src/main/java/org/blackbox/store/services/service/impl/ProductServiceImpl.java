package org.blackbox.store.services.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.blackbox.store.beans.bean.ProductBean;
import org.blackbox.store.beans.entity.Product;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.mappers.mapper.ProductMapper;
import org.blackbox.store.services.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public ResultVO recommendProductsList() {
        List<ProductBean> productBeans = productMapper.selectRecommendProducts();
        return new ResultVO(ResStatus.OK, "success", productBeans);
    }
}
