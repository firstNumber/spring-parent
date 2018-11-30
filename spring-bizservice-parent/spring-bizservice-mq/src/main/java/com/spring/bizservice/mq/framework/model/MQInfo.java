package com.spring.bizservice.mq.framework.model;

import java.util.List;

/**
 * @author liyongzhen
 * @create 2018-11-26 11:24
 **/
public class MQInfo {
    private String name;
    private String exchange;
    private String exchangeType;
    private String routingKey;
    private List<String> dltimes;
    private List<String> queues;

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public List<String> getDltimes() {
        return dltimes;
    }

    public void setDltimes(List<String> dltimes) {
        this.dltimes = dltimes;
    }

    public List<String> getQueues() {
        return queues;
    }

    public void setQueues(List<String> queues) {
        this.queues = queues;
    }

    @Override
    public String toString() {
        return "MQInfo{" +
                "name='" + name + '\'' +
                ", exchange='" + exchange + '\'' +
                ", exchangeType='" + exchangeType + '\'' +
                ", dltimes=" + dltimes +
                ", queues=" + queues +
                '}';
    }
}
