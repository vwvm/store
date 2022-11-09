package org.vwvm.store.services.service.impl;

import org.vwvm.store.beans.entity.IndexImg;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;
import org.vwvm.store.mappers.mapper.IndexImgMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.vwvm.store.services.service.IIndexImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 轮播图  服务实现类
 * </p>
 *
 * @author BlackBox
 * @since 2022-09-02
 */
@Service
public class IndexImgServiceImpl extends ServiceImpl<IndexImgMapper, IndexImg> implements IIndexImgService {

    private final IndexImgMapper indexImgMapper;

    @Autowired
    public IndexImgServiceImpl(IndexImgMapper indexImgMapper) {
        this.indexImgMapper = indexImgMapper;
    }

    @Override
    public ResultVO indexImgList() {
        List<IndexImg> indexImg = indexImgMapper.listIndexImg();
        if (indexImg.isEmpty()) {
            return new ResultVO(ResStatus.NO, "fail", null);
        } else {
            return new ResultVO(ResStatus.OK, "success", indexImg);
        }
    }
}
