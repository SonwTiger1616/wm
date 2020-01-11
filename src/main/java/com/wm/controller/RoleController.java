package com.wm.controller;

import com.wm.controller.viewobject.RoleVO;
import com.wm.dataobject.RoleInfoDO;
import com.wm.response.CommonReturnType;
import com.wm.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller("role")
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @CrossOrigin
    @RequestMapping("/list")
    @ResponseBody
    public CommonReturnType getRoles() {
        List<RoleInfoDO> roleInfoDOList = roleService.getAllRole();
        List<RoleVO> roleVOList = new ArrayList<>();
        for (RoleInfoDO roleInfoDO : roleInfoDOList) {
            roleVOList.add(convertFromRoleInfoDO(roleInfoDO));
        }
        return CommonReturnType.create(roleVOList);
    }

    private RoleVO convertFromRoleInfoDO(RoleInfoDO roleInfoDO) {
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(roleInfoDO, roleVO);
        roleVO.setIfAdmin(roleInfoDO.getIfAdmin() == 1);
        return roleVO;
    }
}
