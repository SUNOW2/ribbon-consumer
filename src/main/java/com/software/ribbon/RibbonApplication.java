package com.software.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 通过@EnableDiscoveryClient注解让该应用注册成为Eureka客户端应用
 * 通过@EnableCircuitBreaker注解开启断路器功能
 * 此处还可以使用@SpringCloudApplication注解来修饰应用主类，
 * @SpringCloudApplication包含了@SpringBootApplication,@EnableDiscoveryClient,@EnableCircuitBreaker三个注解
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class RibbonApplication {

	/**
	 * @LoadBalanced注解用来给RestTemplate做标记，以使用负载均衡的客户端(LoadBalancerClient)来配置它。
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}
}

/**
 * 服务消费者：消费者应用从服务注册中心获取服务列表，从而使消费者知道去何处调用所需要的服务，
 * Ribbon实现了一套对服务实例的选择策略，从而实现对服务的消费
 */