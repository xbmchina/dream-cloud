package cn.xbmchina.dreamcloudmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DreamCloudManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamCloudManagerApplication.class, args);
	}

}
