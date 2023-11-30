package com.pighand.user.service.project.impl;

import com.pighand.framework.spring.base.BaseServiceImpl;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.domain.project.ProjectTripartitePlatformDomain;
import com.pighand.user.mapper.project.ProjectTripartitePlatformMapper;
import com.pighand.user.service.project.ProjectTripartitePlatformService;
import com.pighand.user.vo.project.ProjectTripartitePlatformVO;
import org.springframework.stereotype.Service;

/**
 * 项目三方平台配置
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Service
public class ProjectTripartitePlatformServiceImpl
    extends BaseServiceImpl<ProjectTripartitePlatformMapper, ProjectTripartitePlatformDomain>
    implements ProjectTripartitePlatformService {

    /**
     * 创建
     *
     * @param projectTripartitePlatformVO
     * @return
     */
    @Override
    public ProjectTripartitePlatformVO create(ProjectTripartitePlatformVO projectTripartitePlatformVO) {
        super.mapper.insert(projectTripartitePlatformVO);

        return projectTripartitePlatformVO;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public ProjectTripartitePlatformDomain find(Long id) {
        //        ProjectTripartitePlatformDomain projectTripartitePlatformDomain =
        //                super.mapper.selectById(id);
        //        return projectTripartitePlatformDomain;
        return null;
    }

    /**
     * 分页或列表
     *
     * @param projectTripartitePlatformVO
     */
    @Override
    public PageOrList<ProjectTripartitePlatformVO> query(ProjectTripartitePlatformVO projectTripartitePlatformVO) {
        //        PageOrList pageInfo = projectTripartitePlatformVO.pageParamOrInit(PageType.NEXT_TOKEN);
        //        return super.mapper.query(pageInfo, projectTripartitePlatformVO);
        return null;
    }

    /**
     * 修改
     *
     * @param projectTripartitePlatformVO
     */
    @Override
    public void update(ProjectTripartitePlatformVO projectTripartitePlatformVO) {
        //        super.mapper.updateById(projectTripartitePlatformVO);
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
