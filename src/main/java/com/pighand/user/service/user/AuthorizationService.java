package com.pighand.user.service.user;

import com.pighand.user.domain.project.ProjectAuthorizationDomain;
import com.pighand.user.vo.user.LoginUser;
import com.pighand.user.vo.user.UserVO;

import java.security.NoSuchAlgorithmException;

/**
 * 授权服务
 *
 * @author wangshuli
 */
public interface AuthorizationService {

    /**
     * 生成hash token
     *
     * @param authorization
     * @param userId
     * @throws NoSuchAlgorithmException
     * @returns hashPrefix + hash(base64)
     */
    String generateHashToken(ProjectAuthorizationDomain authorization, Long userId);

    /**
     * 生成授权
     *
     * @param user UserDomain
     * @throws NoSuchAlgorithmException
     * @returns token
     */
    String generateToken(UserVO user);

    /**
     * 校验token
     *
     * <p>jwt根据_c判断是否校验redis，否则只校验jwt hash校验redis
     *
     * @param authorization
     * @returns userId
     */
    LoginUser checkToken(String authorization);

    /**
     * 登出
     *
     * <p>删除redis中的授权信息
     *
     * @param authorization
     */
    void logout(String authorization);
}
