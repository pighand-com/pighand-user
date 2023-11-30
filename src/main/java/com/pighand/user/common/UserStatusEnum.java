package com.pighand.user.common;

/**
 * 用户状态
 *
 * @author wangshuli
 */
public enum UserStatusEnum {
    /** 正常 */
    NORMAL(10),

    /** 停用 */
    STOP(20);

    private final int value;

    UserStatusEnum(int value) {
        this.value = value;
    }
}
