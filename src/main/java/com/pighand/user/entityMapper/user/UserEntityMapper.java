package com.pighand.user.entityMapper.user;

import com.pighand.user.domain.user.UserDomain;
import com.pighand.user.vo.user.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserVO toVo(UserDomain entity);

    UserDomain toDomain(UserVO vo);
}
