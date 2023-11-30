package com.pighand.user.service.user.tripartite;

import com.pighand.user.common.PlatformEnum;
import com.pighand.user.common.UserStatusEnum;
import com.pighand.user.service.user.AuthorizationService;
import com.pighand.user.service.user.UserExtensionService;
import com.pighand.user.service.user.UserService;
import com.pighand.user.vo.user.UserExtensionVO;
import com.pighand.user.vo.user.UserVO;
import com.pighand.user.vo.user.tripartite.TripartitePlatformUserInfo;
import com.pighand.user.vo.user.tripartite.UserPlatformInfo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 三方平台抽象类
 *
 * <p>T (interface): 三方平台解析code返回的参数格式
 *
 * <p>子类需实现TripartitePlatformService接口
 *
 * @author wangshuli
 */
@Service
public abstract class AbstractTripartitePlatform<T> {

    @Getter
    private final PlatformEnum userSourcePlatform;
    @Autowired
    private UserService userService;
    @Autowired
    private UserExtensionService userExtensionService;
    @Autowired
    private AuthorizationService authorizationService;

    protected AbstractTripartitePlatform() {
        throw new UnsupportedOperationException("Subclasses must call the constructor with a PlatformEnum parameter.");
    }

    public AbstractTripartitePlatform(PlatformEnum userSourcePlatform) {
        this.userSourcePlatform = userSourcePlatform;
    }

    /**
     * 解析code
     *
     * @param projectId     项目id
     * @param code          三方平台code
     * @param anonymousCode 匿名code
     * @return T
     */
    protected abstract T analysisCode(Long projectId, String code, String anonymousCode);

    /**
     * 校验用户在库中是否存在
     *
     * @param projectId    项目id
     * @param analysisInfo CODE解析信息
     * @return userPlatformInfo {@link UserPlatformInfo}
     */
    protected abstract UserPlatformInfo checkUserExist(Long projectId, T analysisInfo);

    /**
     * 获取用户平台信息
     *
     * @param analysisInfo CODE解析信息
     * @return tripartitePlatformUserInfo {@link TripartitePlatformUserInfo}
     */
    protected abstract TripartitePlatformUserInfo getPlatformUserInfo(T analysisInfo);

    /**
     * 同步用户平台信息
     *
     * <p>创建或更新
     *
     * @param projectId        项目id
     * @param analysisInfo     CODE解析信息
     * @param userPlatformInfo 用户平台信息
     * @param newUserStatus    新用户状态
     */
    protected abstract void syncPlatform(Long projectId, T analysisInfo, UserPlatformInfo userPlatformInfo,
        UserStatusEnum newUserStatus);

    /**
     * 使用code登录
     *
     * @see #signInByCode(Long, String, String)
     */
    public String signInByCode(Long projectId, String code) {
        return this.signInByCode(projectId, code, null);
    }

    /**
     * 使用code登录
     *
     * <p>1. 解析code中的平台信息（openid、unionid等）
     *
     * <p>2. 使用平台id查询用户是否存在；不存在则创建
     *
     * <p>3. 创建或更新平台用户表数据
     *
     * <p>4. 查询三方平台的用户信息
     *
     * <p>5. 使用三方平台的用户信息，创建或更新用户扩展数据
     *
     * @param projectId     项目id
     * @param code          三方平台code
     * @param anonymousCode 匿名code
     * @return token
     */
    public String signInByCode(Long projectId, String code, String anonymousCode) {
        // 1. 解析CODE
        T analysisInfo = this.analysisCode(projectId, code, anonymousCode);

        // 2. 用户是否存在
        UserPlatformInfo userPlatformInfo = this.checkUserExist(projectId, analysisInfo);
        boolean isUserExist = userPlatformInfo != null;

        UserVO userInfo = new UserVO();

        // 2.1 创建用户
        UserStatusEnum newUserStatus = null;
        if (!isUserExist) {
            UserVO userVO = new UserVO();
            userVO.setProjectId(projectId);
            userVO.setInitialSourcePlatform(this.userSourcePlatform);

            UserVO newUserInfo = userService.create(userVO);

            newUserStatus = newUserInfo.getStatus();

            userPlatformInfo = new UserPlatformInfo(newUserInfo.getId());
        }
        userInfo.setId(userPlatformInfo.getUserId());

        // 3. 同步用户平台数据
        this.syncPlatform(projectId, analysisInfo, userPlatformInfo, newUserStatus);

        // 4. 查询三方平台数据
        TripartitePlatformUserInfo platFormUserInfo = this.getPlatformUserInfo(analysisInfo);

        // 5. 同步扩展数据
        UserExtensionVO userExtension = new UserExtensionVO();
        userExtension.setNickname(platFormUserInfo.getNickname());
        userExtension.setProfile(platFormUserInfo.getProfile());
        userExtension.setGender(platFormUserInfo.getGender());

        if (!isUserExist) {
            userExtensionService.create(userExtension);
        } else {
            userExtension.setId(userPlatformInfo.getUserId());
            userExtensionService.updateTripartitePlatformInfo(userExtension);
        }

        return authorizationService.generateToken(userInfo);
    }
}
