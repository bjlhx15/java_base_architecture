package com.github.bjlhx15.mybatis.springboot.base.common;

import com.github.bjlhx15.mybatis.springboot.base.entity.ResultStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResultException extends Exception {
    private ResultStatus resultStatus;

    public ResultException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
}
