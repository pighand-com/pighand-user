package com.pighand.user.controller.user;

import com.pighand.framework.spring.api.annotation.*;
import com.pighand.framework.spring.api.annotation.validation.ValidationGroup;
import com.pighand.framework.spring.base.BaseController;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.framework.spring.response.Result;
import com.pighand.user.domain.user.UserDomain;
import com.pighand.user.service.user.UserService;
import com.pighand.user.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户关键信息表，主要保存登录所用信息
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@RequiredArgsConstructor
@RestController(path = "user", docName = "用户关键信息表，主要保存登录所用信息")
public class UserController extends BaseController<UserService> {

    /**
     * @param userVO
     * @return
     */
    @Post(docSummary = "创建", fieldGroup = "userCreate")
    public Result<UserVO> create(@Validated({ValidationGroup.Create.class}) @RequestBody UserVO userVO) {
        userVO = super.service.create(userVO);

        return new Result(userVO);
    }

    /**
     * @param id
     * @return
     */
    @Get(path = "{id}", docSummary = "详情")
    public Result<UserDomain> find(@PathVariable(name = "id") Long id) {
        UserDomain userDomain = super.service.find(id);

        return new Result(userDomain);
    }

    /**
     * @param userVO
     */
    @Get(docSummary = "分页或列表", fieldGroup = "userQuery")
    public Result<PageOrList<UserVO>> query(UserVO userVO) {
        PageOrList<UserVO> result = super.service.query(userVO);

        return new Result(result);
    }

    /**
     * @param userVO
     */
    @Put(path = "{id}", docSummary = "修改", fieldGroup = "userUpdate")
    public Result update(@PathVariable(name = "id") Long id, @RequestBody UserVO userVO) {
        userVO.setId(id);
        super.service.update(userVO);

        return new Result();
    }

    /**
     * @param id
     */
    @Delete(path = "{id}", docSummary = "删除")
    public Result delete(@PathVariable(name = "id") Long id) {
        super.service.delete(id);
        return new Result();
    }
}
