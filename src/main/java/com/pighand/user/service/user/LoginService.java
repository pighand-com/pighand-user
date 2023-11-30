package com.pighand.user.service.user;

/**
 * 登录相关接口
 */
public interface LoginService {
    /**
     * 用户名密码登录
     *
     * @param username
     * @param password
     * @return token
     */
    String byPassword(String username, String password);
}
