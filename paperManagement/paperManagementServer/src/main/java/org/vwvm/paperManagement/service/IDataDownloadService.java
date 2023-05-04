package org.vwvm.paperManagement.service;

import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.DataDownload;
import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.paperManagement.entity.requsetEntity.RqPage;

/**
 * <p>
 * 资源下载信息信息 服务类
 * </p>
 *
 * @author BlackBox
 * @since 2023-04-03
 */
public interface IDataDownloadService extends IService<DataDownload> {

    ResultsVO dataDownloadList(Integer pageNum, Integer pageSize);

    ResultsVO getDownloadListByTeacher(Integer pageNum, Integer pageSize, String teacherId);

    Object getDownloadListByTeacher(RqPage rqPage);
}
