package org.blackbox.store.beans.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Users对象", description = "用户买家信息")
public class Users {

  private long userId;
  private String username;
  private String password;
  private String nickname;
  private String realname;
  private String userImg;
  private String userMobile;
  private String userEmail;
  private String userSex;
  private Date userBirth;
  private Date userRegtime;
  private Date userModtime;


}
