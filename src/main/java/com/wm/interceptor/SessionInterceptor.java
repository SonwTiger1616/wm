package com.wm.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wm.dataobject.RoleInfoDO;
import com.wm.error.EmBusinessError;
import com.wm.response.CommonReturnType;
import com.wm.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SessionInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger("SessionInterceptor");
    private boolean flag = false;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String uri = request.getServletPath();
        logger.info("uri:" + uri);
        if (uri.equals("/error")) {
            return true;
        }

        if (uri.contains("/website/get")|| uri.equals("/logout")) {
            return true;
        }
        RoleInfoDO roleInfoDO = LoginUtil.getRole(request);
        flag = !(roleInfoDO == null || ((uri.contains("/user/") || uri.contains("/website/")) && roleInfoDO.getIfAdmin() != 1));
        if(!flag) {
            responseError(response);
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void responseError(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> result = new HashMap<>();
        result.put("errCode", EmBusinessError.NO_ACCESS.getErrCode());
        result.put("errMsg", EmBusinessError.NO_ACCESS.getErrMsg());
        out.println(MAPPER.writeValueAsString(CommonReturnType.create(result, "fail")));
        out.close();
    }

}
