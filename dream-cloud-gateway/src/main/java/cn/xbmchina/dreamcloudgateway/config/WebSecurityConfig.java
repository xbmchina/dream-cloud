package cn.xbmchina.dreamcloudgateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtValidator jwtValidator;

    @Autowired
    AccessControlProperties accessControlProperties;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                .cors().and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().cacheControl();

        //权限校验失败处理在这配置
        httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationFailureHandler())
                .accessDeniedHandler(accessDeniedHandler());

        //从配置文件读取premitAll的配置
        if (accessControlProperties.getPremitAll() != null) {
            for (ResourcePermission rp : accessControlProperties.getPremitAll()) {
                httpSecurity.authorizeRequests().antMatchers(rp.getUrls()).permitAll();
            }
        }
        //从配置文件读取hasAnyRole的配置
        if (accessControlProperties.getHasAnyRole() != null) {
            for (ResourcePermission rp : accessControlProperties.getHasAnyRole()) {
                httpSecurity.authorizeRequests().antMatchers(rp.getUrls()).hasAnyRole(rp.getRoles());
            }
        }
        httpSecurity.authorizeRequests().anyRequest().permitAll();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 解决静态资源被拦截的问题
        //api.ignoring().antMatchers("/**/public/**");
        //从配置文件读取webIgnoring配置
        if (accessControlProperties.getWebIgnoring() != null) {
            for (ResourcePermission rp : accessControlProperties.getWebIgnoring()) {
                web.ignoring().antMatchers(rp.getUrls());
            }
        }
    }

    @Bean
    public CustomAuthenticationEntryPoint authenticationFailureHandler() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public MyAccessDeniedHandler accessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter(jwtValidator);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new      UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
