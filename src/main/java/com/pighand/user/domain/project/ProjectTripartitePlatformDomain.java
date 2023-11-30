package com.pighand.user.domain.project;

import com.mybatisflex.annotation.Table;
import com.pighand.framework.spring.api.annotation.field.RequestFieldException;
import com.pighand.framework.spring.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 项目三方平台配置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Data
@Table("project_tripartite_platform")
public class ProjectTripartitePlatformDomain extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @RequestFieldException("projectTripartitePlatformCreate")
    @RequestFieldException("projectTripartitePlatformUpdate")
    private Long id;

    @Length(max = 255)
    @Schema(description = "公众号重定向url")
    private String officiallyAccountRedirect;
}
