package com.pighand.user.interceptor;

import com.pighand.user.vo.user.LoginUser;

/**
 * 拦截器上下文
 */
public class Context {
    /**
     * 获取项目id
     *
     * @return
     */
    public static Long getProjectId() {
        return HeaderInterceptor.getProjectId();
    }

    /**
     * 获取登录用户
     *
     * @return null 未登录
     */
    public static LoginUser getLoginUser() {
        return AuthorizationInterceptor.getLoginUser();
    }
}
