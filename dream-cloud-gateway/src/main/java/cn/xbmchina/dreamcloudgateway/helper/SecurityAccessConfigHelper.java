package cn.xbmchina.dreamcloudgateway.helper;


import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class SecurityAccessConfigHelper {
    public static final String PERMIT_ALL = "permitAll";
    public static final String DENY_ALL = "denyAll";
    public static final String ANONYMOUS = "anonymous";
    public static final String AUTHENTICATED = "authenticated";
    public static final String FULLY_AUTHENTICATED = "fullyAuthenticated";
    public static final String REMEMBER_ME = "rememberMe";
    private StringBuilder access = new StringBuilder();

    public SecurityAccessConfigHelper() {
    }

    public SecurityAccessConfigHelper permitAll() {
        this.and();
        this.access.append("permitAll");
        return this;
    }

    public SecurityAccessConfigHelper denyAll() {
        this.and();
        this.access.append("denyAll");
        return this;
    }

    public SecurityAccessConfigHelper anonymous() {
        this.and();
        this.access.append("anonymous");
        return this;
    }

    public SecurityAccessConfigHelper authenticated() {
        this.and();
        this.access.append("authenticated");
        return this;
    }

    public SecurityAccessConfigHelper fullyAuthenticated() {
        this.and();
        this.access.append("fullyAuthenticated");
        return this;
    }

    public SecurityAccessConfigHelper rememberMe() {
        this.and();
        this.access.append("rememberMe");
        return this;
    }

    public SecurityAccessConfigHelper hasAnyRole(String... authorities) {
        String anyAuthorities = StringUtils.arrayToDelimitedString(authorities, "','ROLE_");
        this.and();
        this.access.append("hasAnyRole('ROLE_" + anyAuthorities + "')");
        return this;
    }

    public SecurityAccessConfigHelper hasRole(String role) {
        Assert.notNull(role, "role cannot be null");
        if (role.startsWith("ROLE_")) {
            throw new IllegalArgumentException("role should not start with 'ROLE_' since it is automatically inserted. Got '" + role + "'");
        } else {
            this.and();
            this.access.append("hasRole('ROLE_" + role + "')");
            return this;
        }
    }

    public SecurityAccessConfigHelper hasAnyAuthority(String... authorities) {
        String anyAuthorities = StringUtils.arrayToDelimitedString(authorities, "','");
        this.and();
        this.access.append("hasAnyAuthority('" + anyAuthorities + "')");
        return this;
    }

    public SecurityAccessConfigHelper hasAuthority(String authority) {
        this.and();
        this.access.append("hasAuthority('" + authority + "')");
        return this;
    }

    public SecurityAccessConfigHelper hasIpAddress(String ipAddressExpression) {
        this.and();
        this.access.append("hasIpAddress('" + ipAddressExpression + "')");
        return this;
    }

    public String access() {
        return this.access.toString();
    }

    private SecurityAccessConfigHelper and() {
        if (this.access.length() != 0) {
            this.access.append(" and ");
        }

        return this;
    }
}
