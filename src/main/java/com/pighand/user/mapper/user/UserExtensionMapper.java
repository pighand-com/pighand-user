package com.pighand.user.mapper.user;

import com.pighand.framework.spring.base.BaseMapper;
import com.pighand.user.domain.user.UserExtensionDomain;
import com.pighand.user.vo.user.UserExtensionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户扩展信息。id与user表相同
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Mapper
public interface UserExtensionMapper extends BaseMapper<UserExtensionDomain> {

    /**
     * 分页或列表
     *
     * @param pageInfo
     * @param userExtensionVO
     * @return
     */

    UserExtensionVO join(@Param("userExtensionVO") UserExtensionVO userExtensionVO);
}
