package org.vwvm.store.apiserver.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User  {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 30)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true, length = 30)
    private String email;
    @Column(nullable = true, unique = true, length = 30)
    private String nickName;
    @Column(nullable = false)
    private String regTime;

    @Schema(defaultValue = "创建时间")
    private LocalDateTime creationTime;

    @Schema(defaultValue = "更新时间")
    private LocalDateTime updateTime;

    @Schema(defaultValue = "更新人id")
    private String updateUserId;

    @Schema(defaultValue = "删除标记")
    private Boolean deleteFlag;

    public User() {
    }

    public User(String userName, String passWord, String email, String nickName, String regTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nickName;
        this.regTime = regTime;
    }

}
