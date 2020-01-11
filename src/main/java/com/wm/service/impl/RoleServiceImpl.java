package com.wm.service.impl;

import com.wm.dao.RoleInfoDOMapper;
import com.wm.dataobject.RoleInfoDO;
import com.wm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleInfoDOMapper roleInfoDOMapper;

    @Override
    public List<RoleInfoDO> getAllRole() {
        return roleInfoDOMapper.selectAll();
    }

    @Override
    public RoleInfoDO get(Integer roleId) {
        return roleInfoDOMapper.selectByPrimaryKey(roleId);
    }

}
