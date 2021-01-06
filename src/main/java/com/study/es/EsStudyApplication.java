package com.study.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author zujs
 */
@SpringBootApplication(scanBasePackages = "com.study.es")
public class EsStudyApplication {

	/**
	 * 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
	 */
	@Resource
	private RestTemplateBuilder builder;

	/**
	 * 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
	 */
	@Bean
	public RestTemplate restTemplate() {
		//设置超时时间3秒
		builder.setConnectTimeout(Duration.ofSeconds(3));
		builder.setReadTimeout(Duration.ofSeconds(3));
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EsStudyApplication.class, args);
	}



}
