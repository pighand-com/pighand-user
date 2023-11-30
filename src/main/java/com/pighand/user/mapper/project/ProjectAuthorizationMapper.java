package com.pighand.user.mapper.project;

import com.pighand.framework.spring.base.BaseMapper;
import com.pighand.user.domain.project.ProjectAuthorizationDomain;
import org.apache.ibatis.annotations.Mapper;

/**
 * Authorization配置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Mapper
public interface ProjectAuthorizationMapper extends BaseMapper<ProjectAuthorizationDomain> {

    /**
     * 分页或列表
     *
     * @param pageInfo
     * @param projectAuthorizationVO
     * @return
     */
}
