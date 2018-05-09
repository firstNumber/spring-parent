package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.sql.DataSource;

/**
 * @author liyongzhen
 * @create 2018-05-07 11:44
 **/
@EnableEurekaServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class SpringRegisterServiceApplication {
    //    @Value("${server.port:8671}")
//    private int port;

	public static void main(String[] args) {
		SpringApplication.run(SpringRegisterServiceApplication.class, args);
	}

//    @Bean
//    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
//      EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
//      AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
//      config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
//      config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
//      config.setNonSecurePort(port);
//      config.setDataCenterInfo(info);
//      return config;
//    }
}
