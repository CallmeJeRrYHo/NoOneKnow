package com.hjh.nooneknow.net;

/**
 * Created by HJH on 2016/6/30.
 */
public class Message {

    /**
     * msg : I want have
     * phone_md5 : md5xxx
     * msgId : 123456
     */

    private String msg;
    private String phone_md5;
    private String msgId;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhone_md5() {
        return phone_md5;
    }

    public void setPhone_md5(String phone_md5) {
        this.phone_md5 = phone_md5;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
