package org.vwvm.store.api.controller;

import io.swagger.annotations.Api;
import org.vwvm.store.commons.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("goods")
@Api(value = "提供商品添加、修改、删除及查询的相关接口", tags = "商品管理")
public class GoodsController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO addGoods() {
        return null;
    }

    @RequestMapping(value = "/{gid}", method = RequestMethod.DELETE)
    public ResultVO deleteGoods(@PathVariable("gid") int goodsId) {
        System.out.println(goodsId);
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResultVO updateGoods(int goodsId) {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO listGoods() {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultVO getGoods(@PathVariable("id") int goodsId) {
        System.out.println("get" + goodsId);
        return null;
    }

}
