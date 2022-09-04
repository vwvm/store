package org.blackbox.store.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.blackbox.store.commons.vo.ResultVO;
import org.blackbox.store.services.service.IIndexImgService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 轮播图  前端控制器
 * </p>
 * 1.0.0
 * @author BlackBox
 * @since 2022-09-02
 */
@RestController
@CrossOrigin
@RequestMapping("/index")
@Api(value = "提供轮播图功能", tags = "轮播图")
public class IndexController {

    @Resource
    private IIndexImgService iIndexImgService;

    @ApiOperation("返回轮播图列表")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/img list", method = RequestMethod.GET)
    public ResultVO indexImgList() {
        return iIndexImgService.indexImgList();
    }
}
