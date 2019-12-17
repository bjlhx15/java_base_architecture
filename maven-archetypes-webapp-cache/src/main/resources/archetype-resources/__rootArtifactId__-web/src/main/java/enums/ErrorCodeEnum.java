package ${package}.enums;

/**
 * 错误代码枚举类
 */
public enum ErrorCodeEnum {
    // 成功类响应
    success(200000, "成功"),
    no_response_data(200001, "没有返回数据"),

    //请求类响应码
    Param_does_not_exist(400001, "查找参数不存在"),
    Param_does_not_correct(400002, "所传参数格式不正确"),
    HttpMediaTypeNotSupportedException(400003, "不支持的Content-type类型"),

    result_exception(600000, "待处理服务端异常"),

    ;

    ErrorCodeEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}