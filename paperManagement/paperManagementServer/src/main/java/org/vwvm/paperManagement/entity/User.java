package org.vwvm.paperManagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 用于记录系统的所有用户
 * </p>
 *
 * @author BlackBox
 * @since 2023-02-26
 */
@Schema(name = "User对象", description = "用于记录系统的所有用户")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(autoResultMap = true)
public class User implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名，可用于登录")
    private String userUsername;

    @ApiModelProperty("用户密码，可用于登录")
    private String userPassword;

    @ApiModelProperty("用户电话，可用于登录")
    private String userTelephone;

    @ApiModelProperty("用户邮箱，可用于登录")
    private String userEmail;

    @Schema(description = "用户角色，json数据, list")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> user_roles = List.of("ROLE_user");

    @ApiModelProperty("创建时间")
    private LocalDateTime creationTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private Integer updateUserId;

    @ApiModelProperty("删除标记")
    private Boolean deleteFlag;

    @Schema(description = "账户通过过期校验")
    @TableField(exist = false)
    private boolean accountNonExpired = true;

    @Schema(description = "账户通过锁定校验")
    @TableField(exist = false)
    private boolean accountNonLocked = true;

    @Schema(description = "密码通过过期校验")
    @TableField(exist = false)
    private boolean credentialsNonExpired = true;

    @Schema(description = "账户是否启用")
    @TableField(exist = false)
    private boolean enabled = true;

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (!user_roles.isEmpty()) {
            user_roles.forEach(role -> {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                authorities.add(simpleGrantedAuthority);
            });
        }

        return authorities;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return userPassword;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return userUsername;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getUser_roles() {
        return user_roles;
    }

    public Integer getId() {
        return id;
    }
}
