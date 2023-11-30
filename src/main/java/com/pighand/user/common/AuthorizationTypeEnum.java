package com.pighand.user.common;

/**
 * 授权类型
 *
 * @author wangshuli
 */
public enum AuthorizationTypeEnum {
    /** jwt */
    JWT(1),

    /** hash */
    HASH(2);

    private final int value;

    AuthorizationTypeEnum(int value) {
        this.value = value;
    }
}
