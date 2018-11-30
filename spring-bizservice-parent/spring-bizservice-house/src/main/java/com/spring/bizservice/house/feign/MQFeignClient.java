package com.spring.bizservice.house.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mq-biz-service")
public interface MQFeignClient {
     @RequestMapping(value = "/mq/push", method = RequestMethod.POST,consumes = "application/json")
  public void push(@RequestParam(value = "subject", required = true) String subject,
                   @RequestParam(value = "msg", required = true) String msg,
                   @RequestParam(value = "delaySeconds", required = false, defaultValue = "0") Integer delaySeconds);
}
