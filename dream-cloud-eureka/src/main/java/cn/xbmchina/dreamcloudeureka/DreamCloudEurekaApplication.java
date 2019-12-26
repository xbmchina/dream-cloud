package cn.xbmchina.dreamcloudeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DreamCloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamCloudEurekaApplication.class, args);
	}

}
