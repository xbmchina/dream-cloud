package cn.xbmchina.dreamcloudgateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@ConfigurationProperties(prefix = "app.access-control")
public class AccessControlProperties {
    private List<ResourcePermission> premitAll;
    private List<ResourcePermission> hasAnyRole;
    private List<ResourcePermission> webIgnoring;

    public List<ResourcePermission> getPremitAll() {
        return premitAll;
    }

    public void setPremitAll(List<ResourcePermission> premitAll) {
        this.premitAll = premitAll;
    }

    public List<ResourcePermission> getHasAnyRole() {
        return hasAnyRole;
    }

    public void setHasAnyRole(List<ResourcePermission> hasAnyRole) {
        this.hasAnyRole = hasAnyRole;
    }

    public List<ResourcePermission> getWebIgnoring() {
        return webIgnoring;
    }

    public void setWebIgnoring(List<ResourcePermission> webIgnoring) {
        this.webIgnoring = webIgnoring;
    }
}
