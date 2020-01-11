package com.wm.service;

import com.wm.dataobject.RoleInfoDO;

import java.util.List;

public interface RoleService {

    public List<RoleInfoDO> getAllRole();

    RoleInfoDO get(Integer roleId);
}
