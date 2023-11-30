package com.pighand.user.controller.user;

import com.pighand.framework.spring.api.annotation.Post;
import com.pighand.framework.spring.api.annotation.RestController;
import com.pighand.framework.spring.base.BaseController;
import com.pighand.framework.spring.response.Result;
import com.pighand.user.interceptor.Context;
import com.pighand.user.service.user.UserService;
import com.pighand.user.service.user.tripartite.wechat.AppletImpl;
import com.pighand.user.vo.user.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 登录
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@RequiredArgsConstructor
@RestController(path = "login", docName = "用户关键信息表，主要保存登录所用信息")
public class LoginController extends BaseController<UserService> {

    private final AppletImpl wechatAppletService;

    @Post()
    public Result password(@RequestBody Login login) {
        Long projectId = Context.getProjectId();
        String token = wechatAppletService.signInByCode(projectId, login.getCode());
        return new Result(token);
    }

    @Post("wechat/applet")
    public Result wechatApplet(@RequestBody Login login) {
        Long projectId = Context.getProjectId();
        String token = wechatAppletService.signInByCode(projectId, login.getCode());
        return new Result(token);
    }

}
