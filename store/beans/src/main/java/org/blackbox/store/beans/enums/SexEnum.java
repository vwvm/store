package org.blackbox.store.beans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * <h3>store</h3>
 * <p></p>
 *
 * @author : BlackBox
 * {@code @date} : 2022-06-15 19:39
 **/
@Getter
public enum SexEnum {


    /**
     * 1男2女0未知
     */
    MALE(1, "男"),
    FEMALE(2, "女"),
    UN_MALE(0, "未知");

    //将注解标识的存储到数据库中
    @EnumValue
    private Integer sex;
    private String sexName;


    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }


}
