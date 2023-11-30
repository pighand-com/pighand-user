package com.pighand.user.service.user.impl;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import com.pighand.framework.spring.base.BaseServiceImpl;
import com.pighand.framework.spring.exception.ThrowPrompt;
import com.pighand.framework.spring.page.PageOrList;
import com.pighand.framework.spring.page.PageType;
import com.pighand.framework.spring.util.VerifyUtils;
import com.pighand.user.common.UserStatusEnum;
import com.pighand.user.domain.user.UserDomain;
import com.pighand.user.domain.table.UserTableDef;
import com.pighand.user.interceptor.Context;
import com.pighand.user.mapper.user.UserMapper;
import com.pighand.user.service.user.UserService;
import com.pighand.user.vo.user.CheckUserExist;
import com.pighand.user.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户关键信息表，主要保存登录所用信息
 *
 * @author wangshuli
 * @createDate 2023-03-25 18:45:58
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserDomain> implements UserService {

    private final UserTableDef userTableDef = UserTableDef.USER;

    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     */
    private boolean isPhone(String phone) {
        return phone.matches("^1[3-9]\\d{9}$");
    }

    /**
     * 判断是否是邮箱
     *
     * @param email
     * @return
     */
    private boolean isEmail(String email) {
        return email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }

    /**
     * 查询用户存在信息
     *
     * @param username
     * @param phone
     * @param email
     * @param userId   库中用户id，用来排除当前用户
     * @return
     */
    private CheckUserExist getExistInfo(String username, String phone, String email, Long userId) {
        boolean hasUsername = VerifyUtils.isNotEmpty(username);
        boolean hasPhone = VerifyUtils.isNotEmpty(phone);
        boolean hasEmail = VerifyUtils.isNotEmpty(email);

        QueryChain query = this.queryChain().select(userTableDef.USERNAME, userTableDef.PHONE, userTableDef.EMAIL)
            .where(userTableDef.PROJECT_ID.eq(Context.getProjectId()));

        if (userId != null) {
            query.and(userTableDef.ID.ne(userId));
        }

        if (hasUsername) {
            query.or(userTableDef.USERNAME.eq(username));
        }
        if (hasPhone) {
            query.or(userTableDef.PHONE.eq(phone));
        }
        if (hasEmail) {
            query.or(userTableDef.EMAIL.eq(email));
        }
        List<UserDomain> users = query.list();

        boolean isUsernameExist = false;
        boolean isPhoneExist = false;
        boolean isEmailExist = false;

        for (UserDomain user : users) {
            if (hasUsername && user.getUsername().equals(username)) {
                isUsernameExist = true;
            } else if (hasPhone && user.getPhone().equals(phone)) {
                isPhoneExist = true;
            } else if (hasEmail && user.getEmail().equals(email)) {
                isEmailExist = true;
            }
        }

        return new CheckUserExist(isUsernameExist, isPhoneExist, isEmailExist);
    }

    /**
     * 检测用户是否存在
     * <p>如果用户名是一个手机号，且填写了手机号，手机号必须和用户名一致</p>
     * <p>如果用户名是一个邮箱，且填写了邮箱，邮箱必须和用户名一致</p>
     * <p>如果用户名是一个手机号，未填写手机号，会将用户名设置为手机号</p>
     * <p>如果用户名是一个邮箱，未填写邮箱，会将用户名设置为邮箱</p>
     *
     * @param userVO
     */
    public void checkUserExist(UserVO userVO) {
        String username = userVO.getUsername();
        String phone = userVO.getPhone();
        String email = userVO.getEmail();

        if (VerifyUtils.isEmpty(username)) {
            boolean isPhone = isPhone(username);
            boolean isEmail = isEmail(username);

            if (isPhone && VerifyUtils.isNotEmpty(phone) && !phone.equals(username)) {
                throw new RuntimeException("用户名如果是手机号，请和手机号一致");
            } else if (isEmail && VerifyUtils.isNotEmpty(email) && !email.equals(username)) {
                throw new RuntimeException("用户名如果是邮箱，请和邮箱一致");
            }

            userVO.setPhone(isPhone ? username : phone);
            userVO.setEmail(isEmail ? username : email);
        }

        CheckUserExist checkUserExist = this.getExistInfo(username, phone, email, userVO.getId());

        if (checkUserExist.isUserNameExist()) {
            throw new ThrowPrompt("用户名已存在");
        } else if (checkUserExist.isPhoneExist()) {
            throw new ThrowPrompt("手机号已存在");
        } else if (checkUserExist.isEmailExist()) {
            throw new ThrowPrompt("邮箱已存在");
        }
    }

    /**
     * 创建
     *
     * @param userVO
     * @return
     */
    @Override
    public UserVO create(UserVO userVO) {
        this.checkUserExist(userVO);

        if (VerifyUtils.isNotEmpty(userVO.getPassword())) {
            userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
        }

        userVO.setStatus(UserStatusEnum.NORMAL);
        super.mapper.insert(userVO);

        return userVO;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public UserDomain find(Long id) {
        return this.queryChain()
            .select(userTableDef.ID, userTableDef.USERNAME, userTableDef.PHONE, userTableDef.EMAIL, userTableDef.STATUS)
            .where(userTableDef.PROJECT_ID.eq(Context.getProjectId())).and(userTableDef.ID.eq(id)).one();
    }

    /**
     * 分页或列表
     *
     * @param userVO
     */
    @Override
    public PageOrList<UserVO> query(UserVO userVO) {
        userVO.setPageType(PageType.PAGE);
        userVO.setProjectId(Context.getProjectId());

        // like
        QueryWrapper queryWrapper = new QueryWrapper();
        if (VerifyUtils.isNotEmpty(userVO.getUsername())) {
            queryWrapper.and(userTableDef.USERNAME.like(userVO.getUsername()));
        }
        if (VerifyUtils.isNotEmpty(userVO.getPhone())) {
            queryWrapper.and(userTableDef.PHONE.like(userVO.getPhone()));
        }
        if (VerifyUtils.isNotEmpty(userVO.getEmail())) {
            queryWrapper.and(userTableDef.EMAIL.like(userVO.getEmail()));
        }

        // equal
        if (VerifyUtils.isNotEmpty(userVO.getStatus())) {
            queryWrapper.and(userTableDef.STATUS.eq(userVO.getStatus()));
        }
        if (VerifyUtils.isNotEmpty(userVO.getRoleId())) {
            queryWrapper.and(userTableDef.ROLE_ID.eq(userVO.getRoleId()));
        }

        return this.mapper.page(userVO, UserVO.class);
    }

    /**
     * 修改
     *
     * @param userVO
     */
    @Override
    public void update(UserVO userVO) {
        this.checkUserExist(userVO);

        UserDomain updateDomain = UpdateEntity.of(UserDomain.class, userVO.getId());
        if (VerifyUtils.isNotEmpty(userVO.getUsername())) {
            updateDomain.setUsername(userVO.getUsername());
        }
        if (VerifyUtils.isNotEmpty(userVO.getPhone())) {
            updateDomain.setPhone(userVO.getPhone());
        }
        if (VerifyUtils.isNotEmpty(userVO.getEmail())) {
            updateDomain.setEmail(userVO.getEmail());
        }
        if (VerifyUtils.isNotEmpty(userVO.getStatus())) {
            updateDomain.setStatus(userVO.getStatus());
        }

        this.mapper.update(updateDomain);
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
