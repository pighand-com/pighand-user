package com.pighand.user.service.project;

import com.pighand.framework.spring.base.BaseService;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.common.PlatformEnum;
import com.pighand.user.domain.project.ProjectPlatformKeyDomain;
import com.pighand.user.vo.project.ProjectPlatformKeyVO;

/**
 * 三方平台key
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
public interface ProjectPlatformKeyService extends BaseService<ProjectPlatformKeyDomain> {

    /**
     * 创建
     *
     * @param platformKeyVO
     * @return
     */
    ProjectPlatformKeyVO create(ProjectPlatformKeyVO platformKeyVO);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    ProjectPlatformKeyDomain find(Long id);

    /**
     * 分页或列表
     *
     * @param platformKeyVO
     * @return PageOrList<PlatformKeyVO>
     */
    PageOrList<ProjectPlatformKeyVO> query(ProjectPlatformKeyVO platformKeyVO);

    /**
     * 修改
     *
     * @param platformKeyVO
     */
    void update(ProjectPlatformKeyVO platformKeyVO);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 查询key
     *
     * @param projectId
     * @param platform
     * @returns platformKey {@link ProjectPlatformKeyDomain}
     */
    ProjectPlatformKeyDomain findByPlatform(Long projectId, PlatformEnum platform);
}
