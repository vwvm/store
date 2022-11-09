package org.vwvm.store.adminApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.vwvm.store.services.adminService.IMemberService;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author vwvm
 * @since 2022-09-07
 */
@RestController
@CrossOrigin
@RequestMapping("/member")
@Api(value = "提供用户相关接口", tags = "用户")
public class MemberController1 {

    @Resource
    private IMemberService memberService;

    @RequestMapping("list")
    @ApiOperation(value = "以用户名查询用户", httpMethod = "GET")
    public String memberList() {
        return memberService.list().toString();
    }

    @RequestMapping("hallo")
    @ApiOperation(value = "hello", httpMethod = "GET")
    public String hallo() {
        return "你好啊 2017034641 郑锦鸿";
    }

    @GetMapping("/member_load/{id}") // /member_load/1
    @ApiOperation("以id查询用户")
    public Object member_load(@PathVariable("id") Long id) {
        return memberService.getById(id);
    }

    @GetMapping("/member_loadByName/{name}") // /member_loadByName/admin
    @ApiOperation("以用户名查询用户")
    public Object member_loadByName(@PathVariable("name") String name) {
        return memberService.loadByName(name);
    }

    @GetMapping("/member_count") // /member_count
    @ApiOperation("统计用户名数目")
    public Object member_count() {
        return memberService.count();
    }

    @GetMapping("/member_countByName/{name}") // /member_countByName/s
    @ApiOperation("统计名字带有指定字符串的用户名数目")
    public Object member_countByName(@PathVariable("name") String name){
        return memberService.countByName(name);
    }

    @GetMapping("/member_pager")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页"),
            @ApiImplicitParam(name = "pageSize", value = "一页多少")
    })
    public Object member_pager(@RequestParam("pageNum") Long pageNum,
                               @RequestParam("pageSize") Long pageSize){
        return memberService.pager(pageNum, pageSize);
    }
    @GetMapping("/member_pagerByName")
    @ApiOperation("搜索带指定名字的分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "查找名字中带有的字符串"),
            @ApiImplicitParam(name = "pageNum", value = "第几页"),
            @ApiImplicitParam(name = "pageSize", value = "一页多少")
    })
    public Object member_pagerByName(@RequestParam("name") String name,
                                     @RequestParam("pageNum") Long pageNum, @RequestParam("pageSize") Long pageSize){
        return memberService.pagerByName(name, pageNum, pageSize);
    }


}
