package com.pighand.user.service.user.impl;

import com.pighand.framework.spring.exception.ThrowPrompt;
import com.pighand.framework.spring.util.VerifyUtils;
import com.pighand.user.common.UserStatusEnum;
import com.pighand.user.domain.user.UserDomain;
import com.pighand.user.domain.table.UserTableDef;
import com.pighand.user.entityMapper.user.UserEntityMapper;
import com.pighand.user.interceptor.Context;
import com.pighand.user.service.user.AuthorizationService;
import com.pighand.user.service.user.LoginService;
import com.pighand.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 登录相关接口
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserEntityMapper userEntityMapper;

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @Override
    public String byPassword(String username, String password) {
        UserTableDef userTableDef = UserTableDef.USER;
        Long projectId = Context.getProjectId();
        UserDomain user = userService.queryChain().select(userTableDef.PASSWORD).select(userTableDef.STATUS)
            .where(userTableDef.PROJECT_ID.eq(projectId)).and(userTableDef.USERNAME.eq(username))
            .or(userTableDef.EMAIL.eq(username)).or(userTableDef.PHONE.eq(username)).one();

        if (user == null) {
            throw new ThrowPrompt("用户或密码错误");
        }

        if (UserStatusEnum.STOP.equals(user.getPassword())) {
            throw new ThrowPrompt("您的账号已停用");
        }

        if (VerifyUtils.isEmpty(user.getPassword())) {
            throw new ThrowPrompt("未设置密码，请使用其他方式登录");
        }

        boolean isMatch = passwordEncoder.matches(user.getPassword(), password);
        if (!isMatch) {
            throw new ThrowPrompt("用户或密码错误");
        }

        return authorizationService.generateToken(userEntityMapper.toVo(user));
    }
}
