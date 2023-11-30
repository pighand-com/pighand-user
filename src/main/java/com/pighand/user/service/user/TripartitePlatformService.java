package com.pighand.user.service.user;

/**
 * 三方平台方法
 *
 * @author wangshuli
 */
public interface TripartitePlatformService {

    /**
     * 使用code登录
     *
     * @param projectId     项目id
     * @param code          三方平台code
     * @param anonymousCode 匿名code
     * @return token
     */
    String signInByCode(Long projectId, String code, String anonymousCode);
}
