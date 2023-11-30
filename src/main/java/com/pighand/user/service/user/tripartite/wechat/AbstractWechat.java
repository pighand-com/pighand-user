package com.pighand.user.service.user.tripartite.wechat;

import com.mybatisflex.core.query.QueryWrapper;
import com.pighand.user.common.PlatformEnum;
import com.pighand.user.common.UserStatusEnum;
import com.pighand.user.domain.user.UserWechatDomain;
import com.pighand.user.sdk.wechat.entity.WechatResponse;
import com.pighand.user.service.user.UserWechatService;
import com.pighand.user.service.user.tripartite.AbstractTripartitePlatform;
import com.pighand.user.vo.user.UserWechatVO;
import com.pighand.user.vo.user.tripartite.UserPlatformInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 微信公共方法类
 *
 * @author wangshuli
 */
public abstract class AbstractWechat<T extends WechatResponse> extends AbstractTripartitePlatform<T> {

    @Autowired
    private UserWechatService userWechatService;

    public AbstractWechat(PlatformEnum platform) {
        super(platform);
    }

    /**
     * 校验用户在库中是否存在
     *
     * <p>1. 查询openid是否存在
     *
     * <p>2. 如果openid不存在，或unionid有修改；查询unionid是否存在
     *
     * <p>3. 如果unionid用户存在，则使用此用户的userId
     */
    @Override
    protected UserPlatformInfo checkUserExist(Long projectId, T analysisInfo) {
        // find by openid
        QueryWrapper queryOpenid = QueryWrapper.create().eq(UserWechatDomain::getProjectId, projectId)
            .eq(UserWechatDomain::getOpenid, analysisInfo.getOpenid()).limit(1);
        UserWechatDomain userByOpenid = userWechatService.getOne(queryOpenid);

        // if change unionid, find by unionid
        boolean isChangeUnionid = StringUtils.hasText(analysisInfo.getUnionid()) && userByOpenid.getUnionid()
            .equals(analysisInfo.getUnionid());
        UserWechatDomain userByUnionid = null;
        if (userByOpenid == null || isChangeUnionid) {
            QueryWrapper queryUnionid = QueryWrapper.create().eq(UserWechatDomain::getProjectId, projectId)
                .eq(UserWechatDomain::getUnionid, analysisInfo.getUnionid()).limit(1);
            userByUnionid = userWechatService.getOne(queryUnionid);
        }

        Long openInfoUserId = Optional.ofNullable(userByOpenid).map(UserWechatDomain::getUserId).orElse(null);
        Long unionidInfoUserId = Optional.ofNullable(userByUnionid).map(UserWechatDomain::getUserId).orElse(null);

        return new UserPlatformInfo(Optional.ofNullable(userByOpenid).map(UserWechatDomain::getId).orElse(null),
            Optional.ofNullable(openInfoUserId).orElse(unionidInfoUserId),
            Optional.ofNullable(userByOpenid).map(UserWechatDomain::getOpenid).orElse(null), null,
            Optional.ofNullable(userByOpenid).map(UserWechatDomain::getUnionid).orElse(null));
    }

    /**
     * 同步用户平台信息
     *
     * <p>不存在 - 创建; 存在 & unionid不同 - 更新unionid
     */
    @Override
    protected void syncPlatform(Long projectId, T analysisInfo, UserPlatformInfo userPlatformInfo,
        UserStatusEnum newUserStatus) {
        UserWechatVO userWechatVO = new UserWechatVO();
        if (userPlatformInfo.getDbId() == null) {
            userWechatVO.setProjectId(projectId);
            userWechatVO.setUserId(userPlatformInfo.getUserId());
            userWechatVO.setOpenid(analysisInfo.getOpenid());
            userWechatVO.setUnionid(analysisInfo.getUnionid());
            userWechatVO.setSourcePlatform(super.getUserSourcePlatform());

            userWechatService.create(userWechatVO);
        } else if (!userPlatformInfo.getDbUnionid().equals(analysisInfo.getUnionid())) {
            userWechatVO.setId(userPlatformInfo.getDbId());
            userWechatVO.setUnionid(analysisInfo.getUnionid());
            userWechatService.update(userWechatVO);
        }
    }
}
