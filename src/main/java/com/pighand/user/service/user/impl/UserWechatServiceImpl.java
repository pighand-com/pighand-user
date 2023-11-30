package com.pighand.user.service.user.impl;

import com.pighand.framework.spring.base.BaseServiceImpl;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.user.common.UserStatusEnum;
import com.pighand.user.domain.user.UserWechatDomain;
import com.pighand.user.mapper.user.UserWechatMapper;
import com.pighand.user.service.user.UserWechatService;
import com.pighand.user.vo.user.UserWechatVO;
import org.springframework.stereotype.Service;

/**
 * 用户微信信息表 同一个用户，不同来源，可能存在多个身份，使用unionid关联。所以本表与用户多对一
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Service
public class UserWechatServiceImpl extends BaseServiceImpl<UserWechatMapper, UserWechatDomain>
    implements UserWechatService {

    /**
     * 创建
     *
     * @param userWechatVO
     * @return
     */
    @Override
    public UserWechatVO create(UserWechatVO userWechatVO) {
        userWechatVO.setStatus(UserStatusEnum.NORMAL);
        super.mapper.insert(userWechatVO);

        return userWechatVO;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public UserWechatDomain find(Long id) {
        //        UserWechatDomain userWechatDomain = super.mapper.selectById(id);
        //        return userWechatDomain;
        return null;
    }

    /**
     * 分页或列表
     *
     * @param userWechatVO
     */
    @Override
    public PageOrList<UserWechatVO> query(UserWechatVO userWechatVO) {
        //        PageOrList pageInfo = userWechatVO.pageParamOrInit(PageType.NEXT_TOKEN);
        //        return super.mapper.query(pageInfo, userWechatVO);
        return null;
    }

    /**
     * 修改
     *
     * @param userWechatVO
     */
    @Override
    public void update(UserWechatVO userWechatVO) {
        //        super.mapper.updateById(userWechatVO);
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
