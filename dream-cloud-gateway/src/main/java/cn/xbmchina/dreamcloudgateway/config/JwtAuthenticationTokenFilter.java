package cn.xbmchina.dreamcloudgateway.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    final String JWT_HEADER = "Authorization";
    final String TOEKN_HEAD = "Bearer ";

    private JwtValidator jwtValidator;

    public JwtAuthenticationTokenFilter(JwtValidator jwtValidator){
        this.jwtValidator = jwtValidator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JWT_HEADER);
        if(authHeader!=null && authHeader.startsWith(TOEKN_HEAD)){
            final String authToken = authHeader.substring(TOEKN_HEAD.length());
            JwtUserDetails userDetails = jwtValidator.validToken(authToken);
            //校验通过
            if(userDetails!=null){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
