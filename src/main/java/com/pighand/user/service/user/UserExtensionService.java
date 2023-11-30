package com.pighand.user.service.user;

import com.pighand.framework.spring.base.BaseService;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.domain.user.UserExtensionDomain;
import com.pighand.user.vo.user.UserExtensionVO;

/**
 * 用户扩展信息。id与user表相同
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
public interface UserExtensionService extends BaseService<UserExtensionDomain> {

    /**
     * 创建
     *
     * @param userExtensionVO
     * @return
     */
    UserExtensionVO create(UserExtensionVO userExtensionVO);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    UserExtensionDomain find(Long id);

    /**
     * 分页或列表
     *
     * @param userExtensionVO
     * @return PageOrList<UserExtensionVO>
     */
    PageOrList<UserExtensionVO> query(UserExtensionVO userExtensionVO);

    /**
     * 修改
     *
     * @param userExtensionVO
     */
    void update(UserExtensionVO userExtensionVO);

    /**
     * 修改三方平台信息
     *
     * @param userExtensionVO
     */
    void updateTripartitePlatformInfo(UserExtensionVO userExtensionVO);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);
}
