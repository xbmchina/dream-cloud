package cn.xbmchina.dreamcloudgateway.config;

import cn.xbmchina.domain.Role;
import cn.xbmchina.domain.User;
import cn.xbmchina.dreamcloudgateway.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtValidator {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(JwtValidator.class);

    public JwtUserDetails validToken(String token){
        try {
            User user = userService.validToken(token);
            if(user!=null && user!=null){
                return toJwtUserDetail(user);
            }else{
                logger.error("token校验无返回结果,token:"+token+"  返回"+user);
            }
            return null;
        }catch (Exception e){
            logger.error("token校验报错,token:"+token+" 错误信息:"+e.getMessage());
            return null;
        }
    }

    public JwtUserDetails toJwtUserDetail(User user){

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roleList = user.getRoleList();

        for(Role role:roleList){
            //设置权限和角色
            authorities.add(new SimpleGrantedAuthority("write"));
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
//            authorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList("read,ROLE_" + role.getName()));
        }
        return new JwtUserDetails(user.getUsername(),user.getPassword(),authorities);
    }

}
