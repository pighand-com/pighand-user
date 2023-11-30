package com.pighand.user.service.project;

import com.pighand.framework.spring.base.BaseService;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.domain.project.ProjectAuthorizationDomain;
import com.pighand.user.vo.project.ProjectAuthorizationVO;

/**
 * Authorization配置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
public interface ProjectAuthorizationService extends BaseService<ProjectAuthorizationDomain> {

    /**
     * 创建
     *
     * @param projectAuthorizationVO
     * @return
     */
    ProjectAuthorizationVO create(ProjectAuthorizationVO projectAuthorizationVO);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    ProjectAuthorizationDomain find(Long id);

    /**
     * 分页或列表
     *
     * @param projectAuthorizationVO
     * @return PageOrList<ProjectAuthorizationVO>
     */
    PageOrList<ProjectAuthorizationVO> query(ProjectAuthorizationVO projectAuthorizationVO);

    /**
     * 修改
     *
     * @param projectAuthorizationVO
     */
    void update(ProjectAuthorizationVO projectAuthorizationVO);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

}
