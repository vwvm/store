package org.vwvm.paperManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.vwvm.paperManagement.entity.DataDownload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 资源下载信息信息 Mapper 接口
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-03
 */
@Mapper
public interface DataDownloadMapper extends BaseMapper<DataDownload> {

}
