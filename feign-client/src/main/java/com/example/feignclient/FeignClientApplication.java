package com.example.feignclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * author:baofei
 */

@EnableFeignClients
@EnableEurekaClient
@RestController
@SpringBootApplication
public class FeignClientApplication {

	@Autowired
	FeignClientUpload clientUpload;

	@RequestMapping("/test")
	public String test() {
		return clientUpload.upload();
	}

	@FeignClient("feign-server")
	interface FeignClientUpload {
		@RequestMapping(value = "/upload",method = GET)
		String upload();
	}


	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}
}
