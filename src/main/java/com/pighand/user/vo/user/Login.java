package com.pighand.user.vo.user;

import lombok.Data;

/**
 * 登录相关参数
 */
@Data
public class Login {
    // 三方平台登录 - code
    private String code;
    // 三方平台登录 - 匿名code
    private String anonymousCode;

    // 账号登录 - 用户名
    private String username;
    // 账号登录 - 密码
    private String password;
    // 账号登录 - 验证码
    private String verificationCode;
}
