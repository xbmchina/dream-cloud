package cn.xbmchina.dreamcloudgateway;

import cn.xbmchina.dreamcloudgateway.config.RequestTimeGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RefreshScope
public class DreamCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamCloudGatewayApplication.class, args);
	}
	@Bean
	public RequestTimeGatewayFilterFactory requestTimeGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}
}
