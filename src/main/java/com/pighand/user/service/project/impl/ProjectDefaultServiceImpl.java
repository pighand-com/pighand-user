package com.pighand.user.service.project.impl;

import com.pighand.framework.spring.base.BaseServiceImpl;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.domain.project.ProjectDefaultDomain;
import com.pighand.user.mapper.project.ProjectDefaultMapper;
import com.pighand.user.service.project.ProjectDefaultService;
import com.pighand.user.vo.project.ProjectDefaultVO;
import org.springframework.stereotype.Service;

/**
 * 项目默认设置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Service
public class ProjectDefaultServiceImpl extends BaseServiceImpl<ProjectDefaultMapper, ProjectDefaultDomain>
    implements ProjectDefaultService {

    /**
     * 创建
     *
     * @param projectDefaultVO
     * @return
     */
    @Override
    public ProjectDefaultVO create(ProjectDefaultVO projectDefaultVO) {
        super.mapper.insert(projectDefaultVO);

        return projectDefaultVO;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public ProjectDefaultDomain find(Long id) {
        //        ProjectDefaultDomain projectDefaultDomain = super.mapper.selectById(id);
        //        return projectDefaultDomain;
        return null;
    }

    /**
     * 分页或列表
     *
     * @param projectDefaultVO
     */
    @Override
    public PageOrList<ProjectDefaultVO> query(ProjectDefaultVO projectDefaultVO) {
        //        PageOrList pageInfo = projectDefaultVO.pageParamOrInit(PageType.NEXT_TOKEN);
        //        return super.mapper.query(pageInfo, projectDefaultVO);
        return null;
    }

    /**
     * 修改
     *
     * @param projectDefaultVO
     */
    @Override
    public void update(ProjectDefaultVO projectDefaultVO) {
        //        super.mapper.updateById(projectDefaultVO);
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
