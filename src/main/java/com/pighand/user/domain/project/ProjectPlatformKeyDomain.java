package com.pighand.user.domain.project;

import com.mybatisflex.annotation.Table;
import com.pighand.framework.spring.api.annotation.field.RequestFieldException;
import com.pighand.framework.spring.api.annotation.validation.ValidationGroup;
import com.pighand.framework.spring.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 三方平台key
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Data
@Table("project_platform_key")
public class ProjectPlatformKeyDomain extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @RequestFieldException("platformKeyCreate")
    @RequestFieldException("platformKeyUpdate")
    private Long id;

    @NotNull(groups = {ValidationGroup.Create.class})
    private Long projectId;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Schema(description = "三方平台 21公众号 22小程序")
    private Integer platform;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Length(max = 255)
    private String appid;

    @NotNull(groups = {ValidationGroup.Create.class})
    @Length(max = 255)
    private String secret;
}
