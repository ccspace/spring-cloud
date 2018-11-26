package com.example.feignserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class FeignServerApplication {

	@Autowired
	DiscoveryClient client;
	@Autowired
	Registration registration;

	@RequestMapping("/upload")
	public ServiceInstance serviceInstance() {
		List<ServiceInstance> list = client.getInstances(registration.getServiceId());
		System.out.println("ip:" + list.get(0).getHost() + ",port:" + list.get(0).getPort());
		return null;
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignServerApplication.class, args);
	}
}
