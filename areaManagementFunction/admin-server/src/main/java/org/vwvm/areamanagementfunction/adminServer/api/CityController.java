package org.vwvm.areamanagementfunction.adminServer.api;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.*;
import org.vwvm.areamanagementfunction.adminServer.entity.City;
import org.vwvm.areamanagementfunction.adminServer.service.impl.CityServiceImpl;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-21
 */
@RestController
@CrossOrigin
@RequestMapping("/city")
public class CityController {

    private CityServiceImpl cityService;

    @Resource
    public void setCityService(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    @ApiOperation("请求城市列表")
    @ApiImplicitParams({
    })
    public ResponseResult cityList(PageVo pageVo) {
        if (Objects.isNull(pageVo.getPageNum())){
            return new ResponseResult(200, "", cityService.list());
        }
        Page<City> cityPage = cityService.page(new Page<>(pageVo.getPageNum(), pageVo.getPageSize()));
        List<City> cityList = cityPage.getRecords();
        long cityPageTotal = cityPage.getTotal();
        return new ResponseResult(200, cityPageTotal, cityList);

    }

    @PostMapping()
    @ApiOperation("添加城市")
    @ApiImplicitParams({
    })
    public ResponseResult add(@RequestBody City city) {

        boolean b = true;
        try {
            cityService.save(city);
        } catch (Exception e) {
            b = false;
        }

        return new ResponseResult(200, "", b);
    }

    @PutMapping()
    @ApiOperation("修改城市")
    @ApiImplicitParams({
    })
    public ResponseResult edit(@RequestBody City city) {
        boolean b = true;
        try {
            cityService.update(city, new UpdateWrapper<City>().eq("id", city.getId()));
        } catch (Exception e) {
            b = false;
        }
        return new ResponseResult(200, "", b);
    }

    @DeleteMapping()
    @ApiOperation("删除城市")
    @ApiImplicitParams({
    })
    public ResponseResult delete(City city) {
        boolean b;
        try {
            b = cityService.removeById(city.getId());
        } catch (Exception e) {
            b = false;
        }
        return new ResponseResult(200, "", b);
    }
}
