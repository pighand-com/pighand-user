package com.pighand.user.service.project.impl;

import com.pighand.framework.spring.base.BaseServiceImpl;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.domain.project.ProjectAuthorizationDomain;
import com.pighand.user.mapper.project.ProjectAuthorizationMapper;
import com.pighand.user.service.project.ProjectAuthorizationService;
import com.pighand.user.vo.project.ProjectAuthorizationVO;
import org.springframework.stereotype.Service;

/**
 * Authorization配置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Service
public class ProjectAuthorizationServiceImpl
    extends BaseServiceImpl<ProjectAuthorizationMapper, ProjectAuthorizationDomain>
    implements ProjectAuthorizationService {

    /**
     * 创建
     *
     * @param projectAuthorizationVO
     * @return
     */
    @Override
    public ProjectAuthorizationVO create(ProjectAuthorizationVO projectAuthorizationVO) {
        super.mapper.insert(projectAuthorizationVO);

        return projectAuthorizationVO;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public ProjectAuthorizationDomain find(Long id) {
        //        ProjectAuthorizationDomain projectAuthorizationDomain = super.mapper.selectById(id);
        //        return projectAuthorizationDomain;
        return null;

    }

    /**
     * 分页或列表
     *
     * @param projectAuthorizationVO
     */
    @Override
    public PageOrList<ProjectAuthorizationVO> query(ProjectAuthorizationVO projectAuthorizationVO) {
        //        PageOrList pageInfo = projectAuthorizationVO.pageParamOrInit(PageType.NEXT_TOKEN);
        //        return super.mapper.query(pageInfo, projectAuthorizationVO);
        return null;

    }

    /**
     * 修改
     *
     * @param projectAuthorizationVO
     */
    @Override
    public void update(ProjectAuthorizationVO projectAuthorizationVO) {
        //        super.mapper.updateById(projectAuthorizationVO);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        super.mapper.deleteById(id);
    }

}
