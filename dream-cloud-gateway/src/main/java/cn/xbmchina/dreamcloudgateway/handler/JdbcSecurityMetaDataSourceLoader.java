package cn.xbmchina.dreamcloudgateway.handler;

import cn.xbmchina.dreamcloudgateway.helper.SecurityAccessConfigHelper;
import cn.xbmchina.dreamcloudgateway.service.UserService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public class JdbcSecurityMetaDataSourceLoader implements SecurityConfigAttributeLoader {

    private UserService userService;

    public JdbcSecurityMetaDataSourceLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> loadConfigAttribute(HttpServletRequest var1) {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMatchers = null;
        //查询数据库将路径以及权限
        requestMatchers = new LinkedHashMap<>();
        SecurityAccessConfigHelper helper = new SecurityAccessConfigHelper();
        AntPathRequestMatcher pathRequestMatcher = new AntPathRequestMatcher("/test2");
        List<ConfigAttribute> configAttributes = SecurityConfig.createList(helper.hasAnyRole("ADMIN","SUPER").access());

        SecurityAccessConfigHelper helper1 = new SecurityAccessConfigHelper();
        AntPathRequestMatcher pathRequestMatcher2 = new AntPathRequestMatcher("/test1/**");
        AntPathRequestMatcher pathRequestMatcher22 = new AntPathRequestMatcher("/user/**");
        List<ConfigAttribute> configAttributes2 = SecurityConfig.createList(helper1.permitAll().access());

        SecurityAccessConfigHelper helper2 = new SecurityAccessConfigHelper();
        AntPathRequestMatcher pathRequestMatcher3 = new AntPathRequestMatcher("/test2/gggg");
        List<ConfigAttribute> configAttributes3 = SecurityConfig.createList(helper2.hasAnyAuthority("write").hasAnyRole("ADMIN").access());

        requestMatchers.put(pathRequestMatcher,configAttributes);
        requestMatchers.put(pathRequestMatcher2,configAttributes2);
        requestMatchers.put(pathRequestMatcher22,configAttributes2);
        requestMatchers.put(pathRequestMatcher3,configAttributes3);

        return requestMatchers;
    }


}
