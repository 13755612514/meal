package com.xyc.mealoperation.constant;

/**
 * 接口返回码, 0表示成功, 其他看对应的定义
 * 0   : 成功
 * > 0 : 表示已知的异常 (例如提示错误等, 需要调用地方单独处理) 客户端的错误请求
 * < 0 : 表示未知的异常 (不需要单独处理, 调用方统一处理) 后端异常错误
 */
public enum ErrorEnum {

    //region ########## HTTP 请求参数相关 ##########
    /**
     * 无效参数
     */
    INVALID_PARAMETER(100, "无效参数"),
    /**
     * 参数缺失
     */
    MISSING_PARAMETER(110, "参数缺失"),
    /**
     * HttpHeader: Content-Type 不支持
     */
    HttpMediaTypeError(120, "Content-Type 不支持"),
    /**
     * HttpHeader: Authorization 格式错误
     */
    AUTHORIZATION_ERROR(130, "Authorization 格式错误"),
    /**
     * Request Method 不支持
     */
    REQUESTMETHOD_ERROR(140, "Request Method 不支持"),
    /**
     * Http body 解析失败
     */
    HTTPMESSAGE_NOTREADABLE(150, "Http body 解析失败"),

    /**
     * 数据绑定错误
     */
    DATABIND_ERROR(160, "输入数据绑定错误"),
    //endregion

    //region ########## 用户认证相关 ##########
    /**
     * 未登录,请先登录
     */
    LOGIN_REQUIRED(401, "未登录,请先登录"),
    /**
     * 登录失败
     */
    LOGIN_ERROR(430, "登录失败"),
    /**
     * 没有权限
     */
    USER_NO_PERMISSION(402, "普通用户没有权限, 不允许访问"),

    ENTERPRISE_NO_PERMISSION(403, "认证用户没有权限, 不允许访问"),

    PAID_NO_PERMISSION(405, "付费用户没有权限, 不允许访问"),
    /**
     * Token 校验失败
     */
    TOKEN_ERROR(420, "Token 校验失败"),
    /**
     * Token 过期
     */
    TOKEN_EXPIRE_ERROR(421, "Token 已过期"),

    /**
     * 密码格式错误
     */
    PASSWORD_FORMAT_ERROR(432, "密码格式错误，要求8-16位数字字母混合字符(无特殊字符)"),


    //region ########## 数据库相关 ##########
    /**
     * 数据已经存在
     */
    DATA_EXIST(170, "数据已经存在"),
    /**
     * 未找到对应数据
     */
    DATA_NOT_FOUND(180, "未找到对应数据"),

    /**
     * 数据库异常
     */
    SQL_EXCEPTION(-100, "数据库异常"),
    //endregion

    //region ########## 其他异常 ##########
    /**
     * 日期格式错误
     */
    DATEFORMAT_ERROR(190, "日期格式错误, 格式需如: yyyy-MM-dd HH:mm:ss"),
    /**
     * 结束日志必须大于起始日期
     */
    DATE_RESTRICTION(200, "结束日志必须大于起始日期"),
    /**
     * 已经超过有效期
     */
    DATE_EXPIRE(210, "已经超过有效期"),
    /**
     * 短信发送失败
     */
    SMS_SEND_FAIL(300, "短信发送失败"),

    /**
     * 邮件验证错误
     */
    EMAIL_VERIFY_ERROR(310, "邮箱地址验证错误"),

    /**
     * 邮件发送错误
     */
    EMAIL_SEND_ERROR(320, "邮件发送错误"),

    /**
     * json 解析异常
     */
    JSON_EXCEPTION(-200, "JSON 解析错误"),
    //endregion

    /**
     * 未知错误类型
     */
    UNKNOWN_EXCEPTION(-999, "未知错误");

    private int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
