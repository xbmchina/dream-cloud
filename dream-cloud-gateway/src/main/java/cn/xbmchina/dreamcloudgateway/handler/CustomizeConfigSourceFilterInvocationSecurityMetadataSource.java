package cn.xbmchina.dreamcloudgateway.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.joor.Reflect;
import org.springframework.expression.ExpressionParser;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CustomizeConfigSourceFilterInvocationSecurityMetadataSource extends DefaultFilterInvocationSecurityMetadataSource {
    private static final Reflect REFLECT = Reflect.on(ExpressionBasedFilterInvocationSecurityMetadataSource.class);
    private SecurityMetadataSource delegate;
    private SecurityConfigAttributeLoader metadataSourceLoader;
    private ExpressionParser expressionParser;

    public CustomizeConfigSourceFilterInvocationSecurityMetadataSource(SecurityMetadataSource delegate, SecurityConfigAttributeLoader metadataSourceLoader) {
        super(new LinkedHashMap());
        this.delegate = delegate;
        this.metadataSourceLoader = metadataSourceLoader;
        this.copyDelegateRequestMap();
    }

    private void copyDelegateRequestMap() {
        Reflect reflect = Reflect.on(this);
        reflect.set("requestMap", this.getDelegateRequestMap());
    }

    private LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> getDelegateRequestMap() {
        Reflect reflect = Reflect.on(this.delegate);
        return (LinkedHashMap)reflect.field("requestMap").get();
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        HttpServletRequest request = ((FilterInvocation)object).getRequest();
        Collection<ConfigAttribute> configAttributes = new ArrayList();
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = this.metadataSourceLoader.loadConfigAttribute(request);
        if (MapUtils.isEmpty(requestMap)) {
            configAttributes.addAll(this.delegate.getAttributes(object));
            return configAttributes;
        } else {
            if (Objects.isNull(this.expressionParser)) {
                SecurityExpressionHandler securityExpressionHandler = GlobalSecurityExpressionHandlerCacheObjectPostProcessor.getSecurityExpressionHandler();
                if (Objects.isNull(securityExpressionHandler)) {
                    throw new NullPointerException(SecurityExpressionHandler.class.getName() + " is null");
                }

                this.expressionParser = securityExpressionHandler.getExpressionParser();
            }

            LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> webExpressionRequestMap = (LinkedHashMap)REFLECT.call("processMap", new Object[]{requestMap, this.expressionParser}).get();
            Iterator var6 = webExpressionRequestMap.entrySet().iterator();

            while(var6.hasNext()) {
                Entry<RequestMatcher, Collection<ConfigAttribute>> entry = (Entry)var6.next();
                if (((RequestMatcher)entry.getKey()).matches(request)) {
                    configAttributes.addAll((Collection)entry.getValue());
                    break;
                }
            }

            configAttributes.addAll(this.delegate.getAttributes(object));
            return configAttributes;
        }
    }
}
