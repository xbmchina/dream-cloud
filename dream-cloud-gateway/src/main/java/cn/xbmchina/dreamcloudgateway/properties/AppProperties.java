package cn.xbmchina.dreamcloudgateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String jwtSecret;
    private int jwtExpirationDay;

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public int getJwtExpirationDay() {
        return jwtExpirationDay;
    }

    public void setJwtExpirationDay(int jwtExpirationDay) {
        this.jwtExpirationDay = jwtExpirationDay;
    }
}
