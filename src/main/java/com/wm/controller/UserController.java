package com.wm.controller;

import com.github.pagehelper.Page;
import com.wm.controller.viewobject.UserVO;
import com.wm.dataobject.RoleInfoDO;
import com.wm.dataobject.UserDO;
import com.wm.error.BusinessException;
import com.wm.error.EmBusinessError;
import com.wm.response.CommonReturnType;
import com.wm.response.PageCommonReturnType;
import com.wm.service.RoleService;
import com.wm.service.UserService;
import com.wm.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.management.relation.RoleInfo;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 添加用户
     *
     * @param name
     * @param account
     * @param telephone
     * @param department
     * @param roleId
     * @return
     * @throws BusinessException
     */
    @CrossOrigin
    @RequestMapping("/add")
    @ResponseBody
    public CommonReturnType addUser(@RequestParam(name = "name") String name,
                                    @RequestParam(name = "account") String account,
                                    @RequestParam(name = "password") String password,
                                    @RequestParam(name = "telephone") String telephone,
                                    @RequestParam(name = "department") String department,
                                    @RequestParam(name = "roleId") Integer roleId) throws BusinessException {
        UserDO userDO = userService.getUserByAccount(account);
        if (userDO != null) {
            throw new BusinessException(EmBusinessError.USER_ACCOUNT_EXIST);
        }
        userDO = new UserDO();
        userDO.setName(name);
        userDO.setAccount(account);
        userDO.setPassword(password);
        userDO.setTelephone(telephone);
        userDO.setRoleId(roleId);
        userDO.setDepartment(department);
        int inserted = userService.addUser(userDO);
        if (inserted > 0) {
            return CommonReturnType.create("操作成功!");
        }
        throw new BusinessException(EmBusinessError.OPTION_FAIL);
    }

    /**
     * 修改用户
     *
     * @param id
     * @param name
     * @param account
     * @param telephone
     * @param department
     * @param roleId
     * @return
     * @throws BusinessException
     */
    @CrossOrigin
    @RequestMapping("/update")
    @ResponseBody
    public CommonReturnType updateUser(@RequestParam(name = "id") Integer id,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "account") String account,
                                       @RequestParam(name = "telephone") String telephone,
                                       @RequestParam(name = "department") String department,
                                       @RequestParam(name = "roleId") Integer roleId) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setName(name);
        userDO.setAccount(account);
        userDO.setTelephone(telephone);
        userDO.setRoleId(roleId);
        userDO.setDepartment(department);
        int updated = userService.updateUser(userDO);
        if (updated > 0) {
            return CommonReturnType.create("操作成功!");
        }
        throw new BusinessException(EmBusinessError.OPTION_FAIL);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @CrossOrigin
    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturnType deleteUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (id == 1) {
            throw new BusinessException(EmBusinessError.OPTION_FAIL);
        }

        int deleted = userService.deleteUserById(id);
        if (deleted > 0) {
            return CommonReturnType.create("操作成功!");
        }
        throw new BusinessException(EmBusinessError.OPTION_FAIL);
    }

    @CrossOrigin
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserDO userDO = userService.getUserById(id);

        // 若用户信息不存在
        if (userDO == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromUserDO(userDO);
        return CommonReturnType.create(userVO);
    }

    @CrossOrigin
    @RequestMapping("/list")
    @ResponseBody
    public PageCommonReturnType getUser(@RequestParam(name = "pageNum") Integer pageNum,
                                        @RequestParam(name = "pageSize") Integer pageSize,
                                        Integer draw) throws BusinessException {
        if (pageNum == null || pageNum <= 0 || pageSize == null || pageSize <= 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Map<Integer, String> roleMap = new HashMap<>();
        Page<UserDO> userDOS = userService.selectUserListByPage(pageNum, pageSize);
        Page<UserVO> userVOS = new Page<>();
        BeanUtils.copyProperties(userDOS, userVOS);
        userVOS.clear();
        for (UserDO userDO : userDOS) {
            UserVO userVO = convertFromUserDO(userDO);
            userVOS.add(userVO);
            String roleName = roleMap.get(userDO.getRoleId());
            if (StringUtils.isEmpty(roleName)) {
                RoleInfoDO roleInfo = roleService.get(userDO.getRoleId());
                if (roleInfo != null) {
                    roleMap.put(roleInfo.getId(), roleInfo.getName());
                    roleName = roleMap.get(userDO.getRoleId());
                }
            }
            userVO.setRoleName(roleName);
        }
        return PageCommonReturnType.create(userVOS, draw);
    }

    /**
     * 修改密码
     *
     * @param account
     * @param password
     * @return
     * @throws BusinessException
     */
    @CrossOrigin
    @RequestMapping("/resetPassword")
    @ResponseBody
    public CommonReturnType modifyPassword(HttpServletRequest request, @RequestParam(name = "account") String account,
                                           @RequestParam(name = "password") String password) throws BusinessException {

        int result = userService.modifyPassword(account, password);
        if (result > 0) {
            return CommonReturnType.create("操作成功");
        }
        throw new BusinessException(EmBusinessError.OPTION_FAIL);
    }


    private UserVO convertFromUserDO(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        userVO.setCreateTime(DateFormatUtils.format(userDO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        return userVO;
    }


}
