package org.vwvm.store.mappers.mapper;

import org.vwvm.store.beans.entity.IndexImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 轮播图  Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-02
 */
@Component
public interface IndexImgMapper extends BaseMapper<IndexImg> {

    //查询轮播图：status=1 按照seq排序
    List<IndexImg> listIndexImg();

}
