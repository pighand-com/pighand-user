package com.pighand.user.common;

/**
 * @author wangshuli
 */
public enum PlatformEnum {
    // 系统直接注册
    SYSTEM_SIGN_IN(10),

    // 微信 - 公众号
    WECHAT_OFFICIALLY_ACCOUNT(21),

    // 微信 - 小程序
    WECHAT_MINI_PROGRAM(22),

    // 抖音小游戏
    DOUYIN_MINI_GAME(33);

    private final int value;

    PlatformEnum(int value) {
        this.value = value;
    }
}
