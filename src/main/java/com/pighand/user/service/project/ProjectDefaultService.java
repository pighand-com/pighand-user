package com.pighand.user.service.project;

import com.pighand.framework.spring.base.BaseService;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.domain.project.ProjectDefaultDomain;
import com.pighand.user.vo.project.ProjectDefaultVO;

/**
 * 项目默认设置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
public interface ProjectDefaultService extends BaseService<ProjectDefaultDomain> {

    /**
     * 创建
     *
     * @param projectDefaultVO
     * @return
     */
    ProjectDefaultVO create(ProjectDefaultVO projectDefaultVO);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    ProjectDefaultDomain find(Long id);

    /**
     * 分页或列表
     *
     * @param projectDefaultVO
     * @return PageOrList<ProjectDefaultVO>
     */
    PageOrList<ProjectDefaultVO> query(ProjectDefaultVO projectDefaultVO);

    /**
     * 修改
     *
     * @param projectDefaultVO
     */
    void update(ProjectDefaultVO projectDefaultVO);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);
}
