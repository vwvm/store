package org.vwvm.store.commons.vo;

/**
 * 返回格式代码以及含义
 */
public class ResStatus {

    public static final int OK = 0;
    public static final int NO = 10001;

    /**
     * token不合法
     */
    public static final int TOKEN_IS_NOT_VALID = 11001;

    /**
     * 未登录
     */
    public static final int NOT_LOGIN_IN = 20001;

    /**
     * 登录信息过期
     */
    public static final int LOGIN_INFORMATION_EXPIRED = 20002;
}
