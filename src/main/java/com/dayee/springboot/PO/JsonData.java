
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

    public JsonData(){

    }

    public static JsonData buildSuccess(Object result){
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setData(result);
        jsonData.setMessage("成功");
        return jsonData;
    }

    public static JsonData buildException(Object result){
        JsonData jsonData = new JsonData();
        jsonData.setCode(1);
        jsonData.setData(result);
        jsonData.setMessage("异常");
        return jsonData;
    }
}
