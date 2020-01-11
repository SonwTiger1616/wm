package com.wm.controller;

import com.wm.dataobject.RoleInfoDO;
import com.wm.dataobject.UserDO;
import com.wm.error.BusinessException;
import com.wm.error.EmBusinessError;
import com.wm.response.CommonReturnType;
import com.wm.service.RoleService;
import com.wm.service.UserService;
import com.wm.util.LoginUtil;
import com.wm.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户登录
     *
     * @param account
     * @param password
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/doLogin")
    public CommonReturnType login(HttpServletRequest request, @RequestParam(name = "account") String account,
                                  @RequestParam(name = "password") String password) throws BusinessException {
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(EmBusinessError.ACCOUNT_OR_PASSWORD_BLANK);
        }
        UserDO userDO = userService.getUserByAccount(account);
        if (userDO == null) {
            throw new BusinessException(EmBusinessError.ACCOUNT_OR_PASSWORD_ERROR);
        }
        if (!MD5.verify(password, userDO.getSalt(), userDO.getPassword())) {
            throw new BusinessException(EmBusinessError.ACCOUNT_OR_PASSWORD_ERROR);
        }
        RoleInfoDO roleInfoDO = roleService.get(userDO.getRoleId());
        HttpSession session = request.getSession();
        System.out.println("login:"+session.getId());
        LoginUtil.setUser(session, userDO);
        LoginUtil.setRole(session, roleInfoDO);
        Map<String,Object> map = new HashMap<>();
        map.put("msg", "登录成功");
        map.put("name", userDO.getName());
        map.put("account", userDO.getAccount());
        map.put("ifAdmin", roleInfoDO.getIfAdmin() == 1);
        return CommonReturnType.create(map);
    }

    @RequestMapping("/logout")
    public CommonReturnType logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return CommonReturnType.create("注销成功");
    }

    /**
     * 修改密码
     *
     * @param account
     * @param oldPassword
     * @param password
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/modifyPassword")
    public CommonReturnType modifyPassword(HttpServletRequest request, @RequestParam(name = "account") String account,
                                           @RequestParam(name = "oldPassword") String oldPassword,
                                           @RequestParam(name = "password") String password) throws BusinessException {
        if (StringUtils.isAnyBlank(account, oldPassword, password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
//        UserDO userDO = userService.getUserByAccount(account);
        UserDO userDO = (UserDO) request.getSession().getAttribute("userInfo");
        if (userDO == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        if (!MD5.verify(oldPassword, userDO.getSalt(), userDO.getPassword())) {
            throw new BusinessException(EmBusinessError.USER_OLD_PASSWORD_ERROR);
        }
        int result = userService.modifyPassword(account, password);
        if (result > 0) {
            return CommonReturnType.create("操作成功");
        }
        throw new BusinessException(EmBusinessError.OPTION_FAIL);
    }

}
