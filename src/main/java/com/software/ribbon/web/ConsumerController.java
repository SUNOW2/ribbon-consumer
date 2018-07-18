package com.software.ribbon.web;

import com.software.ribbon.conf.ResponseEntity;
import com.software.ribbon.po.*;
import com.software.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConsumerController extends BaseController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    @RequestMapping(value="/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    @RequestMapping(value = "/ribbon-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        return helloService.helloService();
    }

    /**
     * Get请求方式
     */
//    @RequestMapping(value = "getString", method = RequestMethod.GET)
//    public String getString(){
////        getForEntity(String url, Class responseType, Object...urlVariable):由于urlVariables参数是一个数组，所以它的顺序会
////        对应url中占位符定义的数字顺序。
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://USER-SERVICE/user?name={1}", String.class, "didi");
//        String body = responseEntity.getBody();
//        return body;
//    }
//
//    @RequestMapping(value = "getUser", method = RequestMethod.GET)
//    public User getUser() {
//        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://USER_SERVICE/user?name={1}", User.class, "didi");
//        User body = responseEntity.getBody();
//        return body;
//    }
//
//    @RequestMapping(value = "getMap", method = RequestMethod.GET)
//    public String getMap() {
//        Map<String, String > params = new HashMap<>();
//        params.put("name", "dada");
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://USER_SERVICE/user?name={name}", String.class, params);
//        String body = responseEntity.getBody();
//        return body;
//    }
//
//    /**
//     * getForObject函数是对getForEntity的进一步封装，它通过HttpMessageConverterExtractor对HTTP的请求体body内容进行对象转换，
//     * 实现请求直接返回包装好的对象内容
//     */
//    @RequestMapping(value = "getObjectString", method = RequestMethod.GET)
//    public String getObjectString() {
//        String resultStr = restTemplate.getForObject("...", String.class);
//        User resultUser = restTemplate.getForObject("...", User.class);
//        return resultStr;
//    }
//
//    /**
//     * POST请求方式
//     */
//
//    @RequestMapping(value = "postString", method = RequestMethod.POST)
//    public String postString() {
//        User user = new User("didi", 30);
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://USER-SERVICE", user, String.class);
//        String body = responseEntity.getBody();
//        return body;
//    }
//
//    /**
//     * PUT请求方式
//     */
//    @RequestMapping(value = "putVoid", method = RequestMethod.PUT)
//    public void putVoid() {
//        Long id = 10001L;
//        User user = new User("didi", 40);
//        restTemplate.put("http://USER-SERVICE/user/{1}", user, id);
//    }

    /**
     * DELETE请求方式
     */
//    @RequestMapping(value = )

    /**
     * POST方式
     * 注意点：服务提供者的接口数据部分需要添加@RequestBody注解
     * @param loginPo
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity insert(LoginPo loginPo) {
        System.out.println("loginPo = " + loginPo);

        return helloService.saveRecord(loginPo);
    }

    /**
     * POST方式
     * 注意点：服务提供者的接口数据部分需要添加@RequestBody注解
     * @param loginTokenPo
     * @return
     */
    @RequestMapping(value = "/tokenLogin", method = RequestMethod.POST)
    public ResponseEntity tokenLogin(LoginTokenPo loginTokenPo) {
        System.out.println("LoginTokenPo = " + loginTokenPo);
        return restTemplate.postForObject("http://PHONE-MESSAGE/user/tokenLogin", loginTokenPo, ResponseEntity.class);
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    public ResponseEntity deleteBatch(PhoneUserDeleteForm phoneUserDeleteForm){
        System.out.println("phoneUserDeleteForm = " + phoneUserDeleteForm);
        return restTemplate.postForObject("http://PHONE-MESSAGE/user/deleteBatch", phoneUserDeleteForm, ResponseEntity.class);
    }

    /**
     * GET方式
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity selectList() {
        return restTemplate.getForObject("http://PHONE-MESSAGE/user/hello", ResponseEntity.class);
    }
}

/**
 * getForEntity(String url, Class responseType, Object...urlVariable):
 * getForEntity(String url, Class responseType, Map urlVariables):
 */