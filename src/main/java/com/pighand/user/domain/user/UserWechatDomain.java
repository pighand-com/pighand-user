package com.pighand.user.domain.user;

import com.mybatisflex.annotation.Table;
import com.pighand.framework.spring.api.annotation.field.RequestFieldException;
import com.pighand.framework.spring.api.annotation.validation.ValidationGroup;
import com.pighand.framework.spring.base.BaseDomain;
import com.pighand.user.common.PlatformEnum;
import com.pighand.user.common.UserStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 用户微信信息表 同一个用户，不同来源，可能存在多个身份，使用unionid关联。所以本表与用户多对一
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Data
@Table("user_wechat")
public class UserWechatDomain extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @RequestFieldException("userWechatCreate")
    @RequestFieldException("userWechatUpdate")
    private Long id;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Schema(description = "项目id")
    private Long projectId;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Schema(description = "用户id")
    private Long userId;

    @Length(max = 64)
    private String openid;

    @Length(max = 64)
    private String unionid;

    @Schema(description = "来源平台 21公众号 22小程序")
    private PlatformEnum sourcePlatform;

    @Schema(description = "状态 10正常 20停用")
    private UserStatusEnum status;
}
