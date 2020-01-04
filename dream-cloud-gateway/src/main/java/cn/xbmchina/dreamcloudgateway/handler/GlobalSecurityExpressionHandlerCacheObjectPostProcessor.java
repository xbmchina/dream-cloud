package cn.xbmchina.dreamcloudgateway.handler;


import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.ObjectPostProcessor;

public class GlobalSecurityExpressionHandlerCacheObjectPostProcessor implements ObjectPostProcessor<SecurityExpressionHandler> {
    private static SecurityExpressionHandler securityExpressionHandler;

    public GlobalSecurityExpressionHandlerCacheObjectPostProcessor() {
    }

    @Override
    public <O extends SecurityExpressionHandler> O postProcess(O object) {
        securityExpressionHandler = object;
        return object;
    }

    public static SecurityExpressionHandler getSecurityExpressionHandler() {
        return securityExpressionHandler;
    }
}
