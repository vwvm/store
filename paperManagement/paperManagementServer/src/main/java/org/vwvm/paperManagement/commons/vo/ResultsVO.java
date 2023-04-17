package org.vwvm.paperManagement.commons.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value = "响应的Result对象", description = "封装接口返回给前端的数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultsVO implements Serializable {

    @ApiModelProperty(value = "响应给前端的状态码", dataType = "int")
    private int code;
    @ApiModelProperty(value = "响应给前端的提示信息", dataType = "String")
    private String msg;
    @ApiModelProperty(value = "响应给前端的数据", dataType = "String")
    private Object data;


    public static ResultsVO succeed(Object data) {
        return succeed(200, "操作成功", data);
    }

    public static ResultsVO succeed(String msg, Object data) {
        return succeed(200, msg, data);
    }

    public static ResultsVO succeed(int code, String msg, Object data) {
        ResultsVO r = new ResultsVO();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static ResultsVO fail(String msg) {
        return fail(400, msg, null);
    }
    public static ResultsVO fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static ResultsVO fail(int code, String msg, Object data) {
        ResultsVO r = new ResultsVO();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
