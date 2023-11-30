package com.pighand.user.sdk.wechat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 公众平台 - access_token
 *
 * @author wangshuli
 */
@Data
public class AccessToken extends WechatResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("is_snapshotuser")
    private Integer isSnapshotuser;

    public AccessToken() {
        super("获取access_token");
    }
}
