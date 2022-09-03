package org.blackbox.store.services.service;

import org.blackbox.store.beans.entity.IndexImg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.blackbox.store.commons.vo.ResultVO;

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
