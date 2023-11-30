package com.pighand.user.service.user.tripartite.wechat;

import com.pighand.user.common.PlatformEnum;
import com.pighand.user.domain.project.ProjectPlatformKeyDomain;
import com.pighand.user.sdk.wechat.WechatSDK;
import com.pighand.user.sdk.wechat.entity.AccessToken;
import com.pighand.user.sdk.wechat.entity.UserInfo;
import com.pighand.user.service.project.ProjectPlatformKeyService;
import com.pighand.user.service.user.TripartitePlatformService;
import com.pighand.user.vo.user.tripartite.TripartitePlatformUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信 - 公众号
 *
 * @author wangshuli
 * @see <a
 * href="https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html">doc</a>
 */
@Service
public class AppletImpl extends AbstractWechat<AccessToken> implements TripartitePlatformService {

    @Autowired
    private ProjectPlatformKeyService platformKeyService;

    AppletImpl() {
        super(PlatformEnum.WECHAT_OFFICIALLY_ACCOUNT);
    }

    /**
     * 解析code
     */
    @Override
    protected AccessToken analysisCode(Long projectId, String code, String anonymousCode) {
        ProjectPlatformKeyDomain key =
            platformKeyService.findByPlatform(projectId, PlatformEnum.WECHAT_OFFICIALLY_ACCOUNT);

        return WechatSDK.OFFICIALLY_ACCOUNT.accessToken(key.getAppid(), key.getSecret(), code, null);
    }

    /**
     * 获取用户平台信息
     */
    @Override
    protected TripartitePlatformUserInfo getPlatformUserInfo(AccessToken analysisInfo) {
        UserInfo userInfo =
            WechatSDK.OFFICIALLY_ACCOUNT.userinfo(analysisInfo.getAccessToken(), analysisInfo.getOpenid());

        return new TripartitePlatformUserInfo(null, userInfo.getNickname(), userInfo.getHeadimgurl(),
            userInfo.getSex());
    }
}
