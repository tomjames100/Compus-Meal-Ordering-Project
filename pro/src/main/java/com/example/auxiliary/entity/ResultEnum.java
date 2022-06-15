package com.example.auxiliary.entity;

public enum ResultEnum {
    // 成功
    SUCCESS(200, "成功"),

    FAIL(-1,"失败"),

    //3 开头 token token异常
    TOKEN_PAST(301, "token过期"), TOKEN_ERROR(302, "token异常"),
    // 登录异常
    LOGIN_ERROR(303, "登录异常"), REMOTE_ERROR(304, "异地登录"),

    // 添加删除更新，4开头
    COURSE_SAVE_FAIL(403, "添加失败"),
    COURSE_UPDATE_FAIL(404, "更新失败"),
    COURSE_DELETE_FAIL(405, "删除失败"),


    // 用户异常，5开头
    LECTURER_REQUISITION_REGISTERED(501, "用户或密码错误"), LECTURER_REQUISITION_WAIT(502, "该用户没有权限"),
//依次往下

    // 错误
    ERROR_SYS(997, "系统异常了....."),
    ERROR_INFAC(998, "出错了请稍后尝试....."),
    ERROR(999, "服务器出错了请稍后尝试....."),
    INVALID_PARAMETERS(1001, "参数异常"),
    //给前台返回提示信息 给用户展示，例如年级名称不能为空等···
    TIPS_INFO(1111,""),


    PHASE_DEL_INFO(4001,"该层级下存在科目或年级，确定要删除吗？"),
    //角色表单验证
    ROLE_EXIST( 6001, "该角色已经存在！");



    private Integer code; //状态码

    private String desc; //描述信息




    private ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }
}
