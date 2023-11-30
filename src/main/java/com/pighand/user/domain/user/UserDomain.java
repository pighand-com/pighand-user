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
import java.util.List;

/**
 * 用户关键信息表，主要保存登录所用信息
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Data
@Table("user")
public class UserDomain extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @RequestFieldException("userCreate")
    @RequestFieldException("userUpdate")
    private Long id;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Schema(description = "项目id")
    private Long projectId;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Schema(description = "角色id")
    private Long roleId;

    @Length(max = 255)
    @Schema(description = "密码")
    private String password;

    @Length(max = 32)
    @Schema(description = "用户名")
    private String username;

    @Length(max = 32)
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "邮箱是否已验证")
    private Boolean emailVerified;

    @Schema(description = "手机号区号")
    private Integer phoneArea;

    @Length(max = 24)
    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "手机号是否已验证")
    private Boolean phoneVerified;

    @Schema(description = "初始来源平台 10系统直接注册 21公众号 22小程序")
    private PlatformEnum initialSourcePlatform;

    @Schema(description = "状态 10正常 20停用")
    private UserStatusEnum status;

    private List<String> test;
}
