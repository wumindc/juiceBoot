package org.juice.web.res;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class WebResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 5121721479543213787L;

    // 操作代码
    Integer code;

    // 提示信息
    String message;

    // 结果数据
    T data;


    public WebResponse(String message) {
        this.message = message;
    }

    public static <T> WebResponse<T> success(T data) {
        WebResponse<T> response = new WebResponse<>("success");
        response.setCode(200);
        response.setData(data);
        return response;
    }
}
