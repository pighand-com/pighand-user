package com.pighand.user.mapper.user;

import com.pighand.framework.spring.base.BaseMapper;
import com.pighand.user.domain.user.UserDomain;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关键信息表，主要保存登录所用信息
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDomain> {

    /**
     * 分页或列表
     *
     * @param pageInfo
     * @param userVO
     * @return
     */
}
