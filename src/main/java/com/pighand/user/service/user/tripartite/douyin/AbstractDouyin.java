package com.pighand.user.service.user.tripartite.douyin;

import com.pighand.user.common.PlatformEnum;
import com.pighand.user.common.UserStatusEnum;
import com.pighand.user.sdk.douyin.entity.DouyinResponse;
import com.pighand.user.service.user.UserWechatService;
import com.pighand.user.service.user.tripartite.AbstractTripartitePlatform;
import com.pighand.user.vo.user.UserWechatVO;
import com.pighand.user.vo.user.tripartite.UserPlatformInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抖音公共方法类
 *
 * @author wangshuli
 */
public abstract class AbstractDouyin<T extends DouyinResponse> extends AbstractTripartitePlatform<T> {

    @Autowired
    private UserWechatService userWechatService;

    public AbstractDouyin(PlatformEnum platform) {
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
        //        LambdaQueryWrapper<UserWechatDomain> queryOpenid = new QueryWrapper().lambda();
        //        queryOpenid
        //                .eq(UserWechatDomain::getProjectId, projectId)
        //                .eq(UserWechatDomain::getOpenid, analysisInfo.getOpenid())
        //                .last("limit 1");
        //        UserWechatDomain userByOpenid = userWechatService.getOne(queryOpenid);
        //
        //        // if change unionid, find by unionid
        //        boolean isChangeUnionid =
        //                StringUtils.hasText(analysisInfo.getUnionid())
        //                        && userByOpenid.getUnionid().equals(analysisInfo.getUnionid());
        //        UserWechatDomain userByUnionid = null;
        //        if (userByOpenid == null || isChangeUnionid) {
        //            LambdaQueryWrapper<UserWechatDomain> queryUnionid = new QueryWrapper().lambda();
        //            queryUnionid
        //                    .eq(UserWechatDomain::getProjectId, projectId)
        //                    .eq(UserWechatDomain::getUnionid, analysisInfo.getUnionid())
        //                    .last("limit 1");
        //            userByUnionid = userWechatService.getOne(queryUnionid);
        //        }
        //
        //        Long openInfoUserId =
        //                Optional.ofNullable(userByOpenid).map(UserWechatDomain::getUserId).orElse(null);
        //        Long unionidInfoUserId =
        //                Optional.ofNullable(userByUnionid).map(UserWechatDomain::getUserId).orElse(null);
        //
        //        return new UserPlatformInfo(
        //                Optional.ofNullable(userByOpenid).map(UserWechatDomain::getId).orElse(null),
        //                Optional.ofNullable(openInfoUserId).orElse(unionidInfoUserId),
        //                Optional.ofNullable(userByOpenid).map(UserWechatDomain::getOpenid).orElse(null),
        //                null,
        //                Optional.ofNullable(userByOpenid).map(UserWechatDomain::getUnionid).orElse(null));
        return null;
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
