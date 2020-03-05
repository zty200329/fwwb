package com.waibao.demo.enums;

import lombok.Getter;

/**
 * @author hobo
 * @description
 */
@Getter
public enum ResultEnum {
    /**
     *
     */
    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式"),
    USER_NOT_EXIST(1, "用户不存在"),
    PASSWORD_ERROR(2, "密码错误"),
    SQL_ERROR(3, "添加用户失败"),
    CODE_ERROR(4, "验证码错误"),
    USER_HAS_EXIST(5, "用户已经存在"),
    PARAMETER_ERROR(6, "参数注意必填项目！"),
    DELETE_ERROR(7,"删除失败"),
    SEARCH_ERROR(8,"查找失败"),
    SEND_ERROR(9,"验证码发送失败"),
    CODE_NOT_EXIST(10,"验证码不存在,请重新获取"),
    USERNAME_HAS_EXIST(11, "用户昵称已经存在"),
    IS_NOT_PERSONAL_OPERATION(12,"用户名不存在,修改密码失败"),
    COMPANY_NOT_EXIST(13,"不存在该公司"),
    COMPANY_PASSWORD_ERROR(14,"企业密码错误"),
    COMPANY_HAS_EXIST(13,"存在该公司");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
