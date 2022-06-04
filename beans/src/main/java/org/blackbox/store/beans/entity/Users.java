package org.blackbox.store.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
    private int userId;
    private String username;
    private String password;
    private String realName;
    private String userImg;
}
