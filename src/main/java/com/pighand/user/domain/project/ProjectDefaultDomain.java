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
 * 项目默认设置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Data
@Table("project_default")
public class ProjectDefaultDomain extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @RequestFieldException("projectDefaultCreate")
    @RequestFieldException("projectDefaultUpdate")
    private Long id;

    @NotNull(groups = {ValidationGroup.Create.class})
    private Long projectId;

    @Length(max = 255)
    @Schema(description = "默认昵称")
    private String defaultNickname;

    @Length(max = 255)
    @Schema(description = "默认头像")
    private String defaultProfile;
}
