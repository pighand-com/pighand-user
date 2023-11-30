package com.pighand.user.service.user.tripartite.douyin;

import com.pighand.user.common.PlatformEnum;
import com.pighand.user.domain.project.ProjectPlatformKeyDomain;
import com.pighand.user.sdk.douyin.entity.AccessToken;
import com.pighand.user.service.project.ProjectPlatformKeyService;
import com.pighand.user.service.user.TripartitePlatformService;
import com.pighand.user.vo.user.tripartite.TripartitePlatformUserInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抖音 - 小游戏
 *
 * @author wangshuli
 */
public class MiniGameImpl extends AbstractDouyin<AccessToken> implements TripartitePlatformService {

    @Autowired
    private ProjectPlatformKeyService platformKeyService;

    MiniGameImpl() {
        super(PlatformEnum.DOUYIN_MINI_GAME);
    }

    /**
     * 解析code
     */
    @Override
    protected AccessToken analysisCode(Long projectId, String code, String anonymousCode) {
        ProjectPlatformKeyDomain key = platformKeyService.findByPlatform(projectId, PlatformEnum.DOUYIN_MINI_GAME);

        //        return DouyinSDK.MINI_GAME.code2session(key.getAppid(), key.getSecret(), code, anonymousCode);
        return null;
    }

    /**
     * 获取用户平台信息
     */
    @Override
    protected TripartitePlatformUserInfo getPlatformUserInfo(AccessToken analysisInfo) {
        return new TripartitePlatformUserInfo();
    }
}
