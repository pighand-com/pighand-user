package com.pighand.user.sdk.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信SDK
 *
 * @author wangshuli
 */
@Service
public class WechatSDK {
    /**
     * 公众平台
     */
    public static OfficiallyAccount OFFICIALLY_ACCOUNT;

    @Autowired(required = false)
    public WechatSDK(OfficiallyAccount officiallyAccount) {
        WechatSDK.OFFICIALLY_ACCOUNT = officiallyAccount;
    }
}
