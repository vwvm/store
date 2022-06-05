package org.blackbox.store.api.controller;

import io.swagger.annotations.Api;
import org.blackbox.store.commons.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("goods")
@Api(value = "提供商品添加、修改、删除及查询的相关接口", tags = "商品管理")
public class GoodsController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO addGoods(){
        return null;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultVO deleteGoods(){
        return null;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public ResultVO updateGoods(int goodsId){
        return null;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultVO listGoods(){
        return null;
    }



}
