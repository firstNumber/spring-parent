package com.spring.bizservice.mq.framework.model;

/**
 * @author liyongzhen
 * @create 2018-11-26 11:32
 **/
public class MQRequestInfo {
    private String subject;
    private String msg;
    private Integer delaySencodes= 0;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getDelaySencodes() {
        return delaySencodes;
    }

    public void setDelaySencodes(Integer delaySencodes) {
        this.delaySencodes = delaySencodes;
    }

    @Override
    public String toString() {
        return "MQRequestInfo{" +
                "subject='" + subject + '\'' +
                ", msg='" + msg + '\'' +
                ", delaySencodes=" + delaySencodes +
                '}';
    }
}
