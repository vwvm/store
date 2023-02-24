package org.vwvm.areamanagementfunction.adminServer.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.PageVo;
import org.vwvm.areamanagementfunction.adminServer.commons.vo.ResponseResult;
import org.vwvm.areamanagementfunction.adminServer.entity.City;
import org.vwvm.areamanagementfunction.adminServer.mapper.CityMapper;
import org.vwvm.areamanagementfunction.adminServer.service.ICityService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-21
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    private CityMapper cityMapper;

    @Resource
    public void setCityMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    public ResponseResult pageList(PageVo pageVo) {

        Wrapper<PageVo> wrapper = new QueryWrapper<>();

        return null;
    }

}
