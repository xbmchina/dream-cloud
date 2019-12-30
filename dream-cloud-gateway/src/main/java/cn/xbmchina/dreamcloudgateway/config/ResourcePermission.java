package cn.xbmchina.dreamcloudgateway.config;


public class ResourcePermission {
    private String[] urls;
    private String[] roles;

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
