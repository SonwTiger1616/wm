package com.wm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.dao.RoleInfoDOMapper;
import com.wm.dao.UserDOMapper;
import com.wm.dataobject.RoleInfoDO;
import com.wm.dataobject.UserDO;
import com.wm.service.UserService;
import com.wm.util.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private RoleInfoDOMapper roleInfoDOMapper;


    @Override
    public UserDO getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        RoleInfoDO roleInfoDO = roleInfoDOMapper.selectByPrimaryKey(userDO.getRoleId());
        userDO.setRoleName(roleInfoDO.getName());
        return userDO;
    }

    @Override
    public int addUser(UserDO userDO) {
        return userDOMapper.insertSelective(addPasswordForUserDO(userDO));
    }

    private UserDO addPasswordForUserDO(UserDO userDO) {
        String salt = MD5.getKey();
        userDO.setSalt(salt);
        userDO.setPassword(MD5.md5(userDO.getPassword(), salt));
        return userDO;
    }

    @Override
    public UserDO getUserByAccount(String account) {
        return userDOMapper.selectByAccount(account);
    }

    @Override
    public int updateUser(UserDO userDO) {
        return userDOMapper.updateByPrimaryKeySelective(userDO);
    }

    @Override
    public int modifyPassword(String account, String password) {
        UserDO userDO = userDOMapper.selectByAccount(account);
        userDO.setPassword(password);
        return updateUser(addPasswordForUserDO(userDO));
    }

    @Override
    public int deleteUserById(Integer id) {
        return userDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page<UserDO> selectUserListByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userDOMapper.selectUserListByPage();
    }

}
