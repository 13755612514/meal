package com.xyc.mealoperation.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author xiongyancong
 * @createTime 2019/11/20 10:05
 * @Description
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    private String msg = "success";

    private int code = SUCCESS;

    private String detail;

    /**
     * maybe null
     */
    private T data;

    private ResultBean(T data) {
        this.data = data;
    }

    private ResultBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<>(data);
    }

    public static <T> ResultBean<T> success() {
        return new ResultBean<>();
    }

    public static <T> ResultBean<T> success(int code, String msg){ return new ResultBean<>(code,msg);}

    public static ResultBean fail(int code, String msg) {
        return new ResultBean<>(code, msg);
    }

    public static ResultBean fail(ErrorEnum errorEnum) {
        return fail(errorEnum.getCode(), errorEnum.getMsg());
    }

    public ResultBean(Throwable e) {
        this.code = ErrorEnum.UNKNOWN_EXCEPTION.getCode();
        this.msg = e.getMessage();
    }

}
