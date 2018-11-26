package com.example.feignserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class FeignServerApplication {

	@Autowired
	DiscoveryClient client;

	@RequestMapping("/upload")
	public String upload() {
		return "";
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignServerApplication.class, args);
	}
}
