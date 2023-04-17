package org.vwvm.paperManagement.commons.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * <h3>admin-server</h3>
 * <p>分页信息</p>
 *
 * @author : BlackBox
 * @date : 2023-02-22 19:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "页码对象", description = "页码信息")
public class PageVo {

    private Integer pageNum;
    private Integer pageSize;
    private String msg;
}