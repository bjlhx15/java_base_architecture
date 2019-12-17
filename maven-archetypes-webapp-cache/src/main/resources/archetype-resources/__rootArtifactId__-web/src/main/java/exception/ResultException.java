package ${package}.exception;

import ${package}.enums.ErrorCodeEnum;

/**
 * 自定义异常类
 */
public class ResultException extends RuntimeException {
    /**
     * 错误码
     * */
    private ErrorCodeEnum errorCodeEnum;

    public ResultException(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    @Override
    public String toString() {
        return "ResultException{" +
                "errorCodeEnum=" + errorCodeEnum +
                '}';
    }
}