package org.blackbox.store.mappers.mapper;

import org.blackbox.store.beans.entity.IndexImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 轮播图  Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-02
 */
public interface IndexImgMapper extends BaseMapper<IndexImg> {

    //查询轮播图：status=1 按照seq排序
    public List<IndexImg> listIndexImg();

}
