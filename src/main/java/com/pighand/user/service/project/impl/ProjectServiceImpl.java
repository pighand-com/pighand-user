package com.pighand.user.service.project.impl;

import com.pighand.framework.spring.base.BaseServiceImpl;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.domain.project.ProjectDomain;
import com.pighand.user.mapper.project.ProjectMapper;
import com.pighand.user.service.project.ProjectService;
import com.pighand.user.vo.project.ProjectVO;
import org.springframework.stereotype.Service;

/**
 * 项目
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectMapper, ProjectDomain> implements ProjectService {

    /**
     * 创建
     *
     * @param projectVO
     * @return
     */
    @Override
    public ProjectVO create(ProjectVO projectVO) {
        super.mapper.insert(projectVO);

        return projectVO;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public ProjectDomain find(Long id) {
        //        ProjectDomain projectDomain = super.mapper.selectById(id);
        //        return projectDomain;
        return null;
    }

    /**
     * 分页或列表
     *
     * @param projectVO
     */
    @Override
    public PageOrList<ProjectVO> query(ProjectVO projectVO) {
        //        PageOrList pageInfo = projectVO.pageParamOrInit(PageType.NEXT_TOKEN);
        //        return super.mapper.query(pageInfo, projectVO);
        return null;
    }

    /**
     * 修改
     *
     * @param projectVO
     */
    @Override
    public void update(ProjectVO projectVO) {
        //        super.mapper.updateById(projectVO);
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
