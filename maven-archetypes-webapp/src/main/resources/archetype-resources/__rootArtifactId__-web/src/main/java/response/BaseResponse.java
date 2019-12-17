package ${package}.response;

import ${package}.enums.ErrorCodeEnum;

/**
 * 统一返回结果
 */
public class BaseResponse<T> {
    /**
     * 返回的data
     * */
    private T data;

    /**
     * 错误码
     * */
    private Integer errorCode;

    /**
     * 错误信息
     * */
    private String errorMsg;

    /**
     * 是否成功
     * */
    private boolean success = false;

    /**
     * 出现异常的构造函数
     * */
    public BaseResponse(ErrorCodeEnum errorCodeEnum) {
        this.errorCode = errorCodeEnum.getErrorCode();
        this.errorMsg = errorCodeEnum.getErrorMsg();
    }

    /**
     * 成功返回的结果
     * */
    public BaseResponse(T data) {
        success = true;
        this.data = data;
    }

    /**
     * 成功返回的结果
     * */
    public BaseResponse(boolean success,ErrorCodeEnum errorCodeEnum,T data) {
        this.success = success;
        this.data = data;
        this.errorCode = errorCodeEnum.getErrorCode();
        this.errorMsg = errorCodeEnum.getErrorMsg();
    }

    public static <T> BaseResponse success(T data) {
        return new BaseResponse(data);
    }
    public static <T> BaseResponse success(ErrorCodeEnum errorCodeEnum) {
        return new BaseResponse(true,errorCodeEnum,null);
    }

    public static BaseResponse fail(ErrorCodeEnum errorCodeEnum) {
        return new BaseResponse(errorCodeEnum);
    }


    public static BaseResponse fail() {
        return new BaseResponse(ErrorCodeEnum.result_exception);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", success=" + success +
                '}';
    }
}