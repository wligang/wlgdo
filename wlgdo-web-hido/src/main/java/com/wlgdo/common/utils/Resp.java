package com.wlgdo.common.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * RPC请求结果封装类
 * 
 * @author: Ligang.Wang[wang_lg@suixingpay.com] 
 * @date:  2017年11月20日 上午10:07:04
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
public class Resp {
    public enum RespCode {

        SUCCESS("0", "SUCCESS"), FAIL("-1", "FAIL"), PARAM_ERROR("-2", "FAIL"), SERVICE_ERROR("-500", "服务异常");

        RespCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

    private String code;
    private String msg;
    private Object data;

    public Resp(Object data) {
        this.code = RespCode.FAIL.code;
        this.msg = RespCode.FAIL.msg;
        this.data = data;
    };

    public Resp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    };

    public Resp(RespCode respCode) {
        this.code = respCode.code;
        this.msg = respCode.msg;
    };

    public Resp(RespCode respCode, Object data) {
        this.code = respCode.code;
        this.msg = respCode.msg;
        this.data = data;
    };

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
