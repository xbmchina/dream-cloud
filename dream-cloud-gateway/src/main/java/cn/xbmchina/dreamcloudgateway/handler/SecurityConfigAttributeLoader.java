package cn.xbmchina.dreamcloudgateway.handler;

import java.util.Collection;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

public interface SecurityConfigAttributeLoader {
    LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> loadConfigAttribute(HttpServletRequest var1);
}
