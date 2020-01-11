package com.wm.util;

import com.wm.dataobject.RoleInfoDO;
import com.wm.dataobject.UserDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {

    private static final String USER_INFO = "userInfo";
    private static final String ROLE_INFO = "roleInfo";

    public static UserDO getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null){
            return null;
        }
        return (UserDO) session.getAttribute(USER_INFO);
    }

    public static RoleInfoDO getRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null){
            return null;
        }
        return (RoleInfoDO) session.getAttribute(ROLE_INFO);
    }

    public static void setRole(HttpSession session, RoleInfoDO roleInfoDO) {
        session.setAttribute(ROLE_INFO, roleInfoDO);
    }

    public static void setUser(HttpSession session, UserDO userDO) {
        session.setAttribute(USER_INFO, userDO);
    }
}
