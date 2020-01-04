package cn.xbmchina.dreamcloudgateway.handler;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class CustomSecurityMetadataSourceObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    private SecurityConfigAttributeLoader securityConfigAttributeLoader;

    public CustomSecurityMetadataSourceObjectPostProcessor(SecurityConfigAttributeLoader securityConfigAttributeLoader) {
        this.securityConfigAttributeLoader = securityConfigAttributeLoader;
    }

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
        FilterSecurityInterceptor interceptor = object;

        CustomizeConfigSourceFilterInvocationSecurityMetadataSource metadataSource =
                new CustomizeConfigSourceFilterInvocationSecurityMetadataSource(
                        interceptor.obtainSecurityMetadataSource() , securityConfigAttributeLoader);
        interceptor.setSecurityMetadataSource(metadataSource);
        return (O) interceptor;
    }
}
