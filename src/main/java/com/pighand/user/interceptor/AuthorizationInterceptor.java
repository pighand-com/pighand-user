package com.pighand.user.interceptor;

import com.pighand.framework.spring.exception.ThrowPrompt;
import com.pighand.framework.spring.util.VerifyUtils;
import com.pighand.user.common.Authorization;
import com.pighand.user.service.user.AuthorizationService;
import com.pighand.user.vo.user.LoginUser;
import io.netty.util.concurrent.FastThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

/**
 * 登录拦截器
 * <p>
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final FastThreadLocal<LoginUser> currentUser = new FastThreadLocal<>();

    @Autowired
    private AuthorizationService authorizationService;

    public static LoginUser getLoginUser() {
        return currentUser.get();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 判断是会否必须登录
        boolean isLoginRequired = false;
        if (handler instanceof HandlerMethod handlerMethod) {
            Authorization methodLogin = handlerMethod.getMethodAnnotation(Authorization.class);
            Authorization classLogin = handlerMethod.getBeanType().getAnnotation(Authorization.class);

            isLoginRequired = methodLogin != null || classLogin != null;

            if (isLoginRequired) {
                isLoginRequired = Optional.ofNullable(methodLogin).map(Authorization::value).orElse(classLogin.value());
            }
        }

        String authorization = request.getHeader("Authorization");
        if (isLoginRequired && VerifyUtils.isEmpty(authorization)) {
            throw new ThrowPrompt("请先登录", 403);
        }

        // 解析token
        if (VerifyUtils.isNotEmpty(authorization)) {
            try {
                LoginUser loginUser = authorizationService.checkToken(authorization);

                currentUser.set(loginUser);
            } catch (Exception e) {
                throw new ThrowPrompt("授权信息错误", 403);
            }
        }

        return true;
    }
}
