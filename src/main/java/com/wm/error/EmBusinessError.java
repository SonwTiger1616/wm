package com.wm.error;

public enum EmBusinessError implements CommonError {
    //通用错误类型
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),
    OPTION_FAIL(10003, "操作失败"),
    NO_ACCESS(10004, "无权访问"),

    //以20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_ACCOUNT_EXIST(20002, "用户账号已存在"),
    USER_OLD_PASSWORD_ERROR(20003, "原始密码错误"),

    //以30000开头为用户登录相关错误定义
    ACCOUNT_OR_PASSWORD_BLANK(30001, "用户名或密码不能为空"),
    ACCOUNT_OR_PASSWORD_ERROR(30002, "用户名或密码错误"),

    //以40000开头为项目文件信息相关错误定义
    PROJECT_FILE_NOT_EXIST(40001, "文件不存在")
    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
