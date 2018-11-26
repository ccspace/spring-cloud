package com.example.feignclient;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	FeignClientAccess clientAccess;

	@RequestMapping("/test")
	public String test() {
		return clientAccess.hello();
	}

	@FeignClient("feign-server")
	interface FeignClientAccess {
		@RequestMapping(value = "/",method = GET)
		String hello();
	}

	/**
	 * feign上传文件
	 */
	@FeignClient(value = "feign-server",configuration = UploadService.MultipartSupportConfig.class)
	interface UploadService{
		@RequestMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		String uploadFile(@RequestPart("file") MultipartFile file);

		@Configuration
		class MultipartSupportConfig{
			@Bean
			public Encoder feignFormEncoder(){
				return new SpringFormEncoder();
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}
}
