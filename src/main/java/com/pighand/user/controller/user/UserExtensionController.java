package com.pighand.user.controller.user;

import com.pighand.framework.spring.api.annotation.*;
import com.pighand.framework.spring.api.annotation.validation.ValidationGroup;
import com.pighand.framework.spring.base.BaseController;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.framework.spring.response.Result;
import com.pighand.user.domain.user.UserExtensionDomain;
import com.pighand.user.service.user.UserExtensionService;
import com.pighand.user.vo.user.UserExtensionVO;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户扩展信息。id与user表相同
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@RestController(path = "user/extension", docName = "用户扩展信息。id与user表相同")
public class UserExtensionController extends BaseController<UserExtensionService> {

    /**
     * @param userExtensionVO
     * @return
     */
    @Post(docSummary = "创建", fieldGroup = "userExtensionCreate")
    public Result<UserExtensionVO> create(
        @Validated({ValidationGroup.Create.class}) @RequestBody UserExtensionVO userExtensionVO) {
        userExtensionVO = super.service.create(userExtensionVO);

        return new Result(userExtensionVO);
    }

    /**
     * @param id
     * @return
     */
    @Get(path = "{id}", docSummary = "详情")
    public Result<UserExtensionDomain> find(@PathVariable(name = "id") Long id) {
        UserExtensionDomain userExtensionDomain = super.service.find(id);

        return new Result(userExtensionDomain);
    }

    /**
     * @param userExtensionVO
     */
    @Get(docSummary = "分页或列表", fieldGroup = "userExtensionQuery")
    public Result<PageOrList<UserExtensionVO>> query(UserExtensionVO userExtensionVO) {
        PageOrList<UserExtensionVO> result = super.service.query(userExtensionVO);

        return new Result(result);
    }

    /**
     * @param userExtensionVO
     */
    @Put(path = "{id}", docSummary = "修改", fieldGroup = "userExtensionUpdate")
    public Result update(@PathVariable(name = "id") Long id, @RequestBody UserExtensionVO userExtensionVO) {
        userExtensionVO.setId(id);
        super.service.update(userExtensionVO);

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
