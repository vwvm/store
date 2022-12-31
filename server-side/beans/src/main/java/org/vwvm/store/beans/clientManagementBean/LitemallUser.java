package org.vwvm.store.beans.clientManagementBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LitemallUser {

    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private Byte gender;

    /**
     *
     */
    private LocalDate birthday;

    /**
     *
     */
    private LocalDateTime lastLoginTime;

    /**
     *
     */
    private String lastLoginIp;

    /**
     *
     */
    private Byte userLevel;

    /**
     *
     */
    private String nickname;

    /**
     *
     */
    private String mobile;

    /**
     *
     */
    private String avatar;

    /**
     *
     */
    private String weixinOpenid;

    /**
     *
     */
    private String sessionKey;

    /**
     *
     */
    private Byte status;

    /**
     *
     */
    private LocalDateTime addTime;

    /**
     *
     */
    private LocalDateTime updateTime;

    /**
     *
     */
    private Boolean deleted;

}