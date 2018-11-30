package com.spring.bizservice.mq;

import com.spring.bizservice.mq.framework.model.MQInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author liyongzhen
 * @create 2018-11-26 13:24
 **/
@Configuration
@ConfigurationProperties(prefix = "mq")
public class MQConfig {
    private List<MQInfo> infos;

    public List<MQInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<MQInfo> infos) {
        this.infos = infos;
    }
}
