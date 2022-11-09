package org.vwvm.store.services.service;

import org.vwvm.store.beans.entity.IndexImg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.vwvm.store.commons.vo.ResultVO;

/**
 * <p>
 * 轮播图  服务类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-02
 */
public interface IIndexImgService extends IService<IndexImg> {

    ResultVO indexImgList();
}
