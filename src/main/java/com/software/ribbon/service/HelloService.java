package com.software.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.software.ribbon.conf.ResponseEntity;
import com.software.ribbon.po.LoginPo;
import com.software.ribbon.po.LoginTokenPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    public ResponseEntity saveRecord(LoginPo loginPo) {
        return restTemplate.postForObject("http://PHONE-MESSAGE/user/insert", loginPo, ResponseEntity.class);
    }

    public String helloFallback() {
        return "Error";
    }

    /**
     * 断路器：
     * 出现异常 "fallback method wasn't found: helloFallback([class com.software.ribbon.po.LoginPo])"
     * 这是由于指定的备用方法和原方法参数个数，类型不同造成的
     * @return
     */
    public ResponseEntity helloFallback(LoginPo po) {
        return new ResponseEntity("fail", 500, "新增用户失败", null);
    }
}