package com.pighand.user.controller.user;

import com.pighand.framework.spring.api.annotation.*;
import com.pighand.framework.spring.api.annotation.validation.ValidationGroup;
import com.pighand.framework.spring.base.BaseController;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.framework.spring.response.Result;
import com.pighand.user.domain.user.UserWechatDomain;
import com.pighand.user.service.user.UserWechatService;
import com.pighand.user.vo.user.UserWechatVO;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户微信信息表 同一个用户，不同来源，可能存在多个身份，使用unionid关联。所以本表与用户多对一
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@RestController(path = "user/wechat", docName = "用户微信信息表 同一个用户，不同来源，可能存在多个身份，使用unionid关联。所以本表与用户多对一")
public class UserWechatController extends BaseController<UserWechatService> {

    /**
     * @param userWechatVO
     * @return
     */
    @Post(docSummary = "创建", fieldGroup = "userWechatCreate")
    public Result<UserWechatVO> create(
        @Validated({ValidationGroup.Create.class}) @RequestBody UserWechatVO userWechatVO) {
        userWechatVO = super.service.create(userWechatVO);

        return new Result(userWechatVO);
    }

    /**
     * @param id
     * @return
     */
    @Get(path = "{id}", docSummary = "详情")
    public Result<UserWechatDomain> find(@PathVariable(name = "id") Long id) {
        UserWechatDomain userWechatDomain = super.service.find(id);

        return new Result(userWechatDomain);
    }

    /**
     * @param userWechatVO
     */
    @Get(docSummary = "分页或列表", fieldGroup = "userWechatQuery")
    public Result<PageOrList<UserWechatVO>> query(UserWechatVO userWechatVO) {
        PageOrList<UserWechatVO> result = super.service.query(userWechatVO);

        return new Result(result);
    }

    /**
     * @param userWechatVO
     */
    @Put(path = "{id}", docSummary = "修改", fieldGroup = "userWechatUpdate")
    public Result update(@PathVariable(name = "id") Long id, @RequestBody UserWechatVO userWechatVO) {
        userWechatVO.setId(id);
        super.service.update(userWechatVO);

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
