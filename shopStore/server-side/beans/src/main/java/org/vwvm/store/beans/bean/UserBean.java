package org.vwvm.store.beans.bean;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>store</h3>
 * <p> 用户注册Bean </p>
 *
 * @author : BlackBox
 * @date : 2023-03-11 19:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User注册对象", description = "用户信息")
public class UserBean {
    private String username;
    private String password;
}
