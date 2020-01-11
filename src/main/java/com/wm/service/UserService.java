package com.wm.service;

import com.github.pagehelper.Page;
import com.wm.dataobject.UserDO;

public interface UserService {

    UserDO getUserById(Integer id);

    int addUser(UserDO userDO);

    UserDO getUserByAccount(String account);

    int updateUser(UserDO userDO);

    int modifyPassword(String account, String password);

    int deleteUserById(Integer id);

    Page<UserDO> selectUserListByPage(Integer pageNum, Integer pageSize);
}
