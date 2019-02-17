package com.bawei.rikao2_14.bean;

import java.util.ArrayList;

/**
 * @author 王艺霏
 * @fileName JsonBean
 * @package com.bawei.rikao2_14.bean
 **/
public class JsonBean {
    private String code;
    private ArrayList<Step> data;
    private String msg;

    public JsonBean(String code, ArrayList<Step> data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonBean() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Step> getData() {
        return data;
    }

    public void setData(ArrayList<Step> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
