package org.vwvm.areamanagementfunction.adminServer.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.PageVo;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.RegionInfo;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.ResponseResult;
import org.vwvm.areamanagementfunction.adminServer.entity.City;
import org.vwvm.areamanagementfunction.adminServer.entity.Region;
import org.vwvm.areamanagementfunction.adminServer.service.impl.RegionServiceImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/region")
public class RegionController {

    private RegionServiceImpl regionService;

    @Resource
    public void setRegionService(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @GetMapping()
    @ApiOperation("请求区域列表")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "query", value = "查询名称", required = true),
    })
    public ResponseResult menus(PageVo pageVo) {
        Map<String, String> query = pageVo.getQuery();
        System.out.println(pageVo);
        QueryWrapper<Region> regionQueryWrapper = new QueryWrapper<>();
        if (!query.get("address").isEmpty()) {
            regionQueryWrapper.like("address", query.get("address"));
        }
        if (!query.get("regionCity").isEmpty()) {
            regionQueryWrapper.like("regional_city", query.get("regionCity"));
        }
        if (!query.get("regionName").isEmpty()) {
            regionQueryWrapper.like("region_name", query.get("regionName"));
        }
        if (!Objects.equals(query.get("creationTimeA"), "NaN")) {
            regionQueryWrapper.between("creation_time",
                    new Date(Long.parseLong(query.get("creationTimeA"))),
                    new Date(Long.parseLong(query.get("creationTimeB"))));
        }
        Page<Region> regionPage = regionService.page(
                new Page<>(pageVo.getPageNum(), pageVo.getPageSize()),
                regionQueryWrapper);
        List<Region> regionList = regionPage.getRecords();
        long regionPageTotal = regionPage.getTotal();
        return new ResponseResult(200, regionPageTotal, regionList);
    }

    @PostMapping()
    @ApiOperation("添加城市")
    @ApiImplicitParams({
    })
    public ResponseResult add(@RequestBody Region region) {

        boolean b = true;
        try {
            regionService.save(region);
        } catch (Exception e) {
            b = false;
        }

        return new ResponseResult(200, "", b);
    }

    @PutMapping()
    @ApiOperation("修改城市")
    @ApiImplicitParams({
    })
    public ResponseResult edit(@RequestBody Region region) {
        boolean b = true;
        try {
            regionService.update(region, new UpdateWrapper<Region>().eq("id", region.getId()));
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
            b = regionService.removeById(city.getId());
        } catch (Exception e) {
            b = false;
        }
        return new ResponseResult(200, "", b);
    }
}
