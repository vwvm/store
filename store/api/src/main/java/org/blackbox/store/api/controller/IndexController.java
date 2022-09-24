package org.blackbox.store.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.services.service.ICategoryService;
import org.blackbox.store.services.service.IIndexImgService;
import org.blackbox.store.services.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 轮播图  前端控制器
 * </p>
 * 1.0.0
 *
 * @author BlackBox
 * @since 2022-09-02
 */
@RestController
@CrossOrigin
@RequestMapping("/index")
@Api(value = "提供首页所需的功能", tags = "首页接口")
public class IndexController {

    @Resource
    private IIndexImgService iIndexImgService;
    @Resource
    private IProductService productService;
    @Resource
    private ICategoryService categoryService;



    @Autowired
    public IndexController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("返回轮播图列表")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/img-list", method = RequestMethod.GET)
    public ResultVO indexImgList() {
        return iIndexImgService.indexImgList();
    }

    @GetMapping("/category-list")
    @ApiOperation("商品类别查询接口")
    public ResultVO categoryList() {
        return categoryService.categoryList();
    }

    @GetMapping("/product-recommends")
    @ApiOperation("新品推荐商品接口")
    public ResultVO productRecommendsList() {

        return productService.recommendProductsList();
    }

    @GetMapping("/category-recommends")
    @ApiOperation("分类推荐商品接口")
    public ResultVO categoryRecommendsList() {

        return categoryService.firstLevelCategoryList();
    }
}
