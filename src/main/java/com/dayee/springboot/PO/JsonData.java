
package com.dayee.springboot.PO;

import java.io.Serializable;

public class JsonData implements Serializable {

    private static final long serialVersionUID = 1663754027362056297L;

    // 状态码 0-成功 -1-失败
    private int               code;

    // 结果
    private Object            data;

    private Object            message;

    public Object getMessage() {

        return message;
    }

    public void setMessage(Object message) {

        this.message = message;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public Object getData() {

        return data;
    }

    public void setData(Object data) {

        this.data = data;
    }

    public JsonData(int code, Object data, Object message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
