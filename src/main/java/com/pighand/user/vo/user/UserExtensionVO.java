package com.pighand.user.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pighand.user.domain.user.UserExtensionDomain;
import com.pighand.user.domain.user.UserWechatDomain;

import lombok.Data;

import java.util.List;

/**
 * 用户扩展信息。id与user表相同
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Data
public class UserExtensionVO extends UserExtensionDomain {
    @JsonIgnoreProperties(value = {"id"})
    private UserVO user;

    @JsonIgnoreProperties(value = {"id"})
    private List<UserWechatDomain> userWechats;
}
