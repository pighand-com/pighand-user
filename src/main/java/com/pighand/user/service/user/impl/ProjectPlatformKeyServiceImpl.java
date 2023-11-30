package com.pighand.user.service.user.impl;

import com.pighand.framework.spring.base.BaseServiceImpl;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.common.PlatformEnum;
import com.pighand.user.domain.project.ProjectPlatformKeyDomain;
import com.pighand.user.mapper.project.ProjectPlatformKeyMapper;
import com.pighand.user.service.project.ProjectPlatformKeyService;
import com.pighand.user.vo.project.ProjectPlatformKeyVO;
import org.springframework.stereotype.Service;

/**
 * 三方平台key
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Service
public class ProjectPlatformKeyServiceImpl extends BaseServiceImpl<ProjectPlatformKeyMapper, ProjectPlatformKeyDomain>
    implements ProjectPlatformKeyService {

    /**
     * 创建
     *
     * @param platformKeyVO
     * @return
     */
    @Override
    public ProjectPlatformKeyVO create(ProjectPlatformKeyVO platformKeyVO) {
        super.mapper.insert(platformKeyVO);

        return platformKeyVO;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public ProjectPlatformKeyDomain find(Long id) {
        //        PlatformKeyDomain platformKeyDomain = super.mapper.selectById(id);
        return null;
    }

    /**
     * 分页或列表
     *
     * @param platformKeyVO
     */
    @Override
    public PageOrList<ProjectPlatformKeyVO> query(ProjectPlatformKeyVO platformKeyVO) {
        //        PageOrList pageInfo = platformKeyVO.pageParamOrInit(PageType.NEXT_TOKEN);
        //        return super.mapper.query(pageInfo, platformKeyVO);
        return null;
    }

    /**
     * 修改
     *
     * @param platformKeyVO
     */
    @Override
    public void update(ProjectPlatformKeyVO platformKeyVO) {
        //        super.mapper.updateById(platformKeyVO);
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

    /**
     * 查询key
     *
     * @param projectId
     * @param platform
     * @returns platformKey {@link ProjectPlatformKeyDomain}
     */
    @Override
    public ProjectPlatformKeyDomain findByPlatform(Long projectId, PlatformEnum platform) {
        //        LambdaQueryChainWrapper<PlatformKeyDomain> queryWrapper = super.lambdaQuery();
        //        queryWrapper.eq(PlatformKeyDomain::getProjectId, projectId);
        //        queryWrapper.eq(PlatformKeyDomain::getPlatform, platform);
        //        queryWrapper.last("limit 1");

        //        PlatformKeyDomain platformKey = super.mapper.selectOne(queryWrapper);
        //
        //        if (platformKey == null) {
        //            throw new ThrowPrompt("未配置三方平台key");
        //        }
        //
        //        return platformKey;
        return null;
    }
}
