package cn.xbmchina.dreamcloudgateway.util;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * JwtToken生成的工具类
 * Created by xbmchina.
 */
public class JwtTokenUtil {

    private int jwtExpirationDay;
    private String jwtSecret;
    private String KEY_USERNAME = "username";
    private SecretKey key;

    public JwtTokenUtil(int jwtExpirationDay, String jwtSecret) {
        this.jwtExpirationDay = jwtExpirationDay;
        this.jwtSecret = jwtSecret;
        byte[] encodedKey = Base64.decodeBase64( this.jwtSecret);
        this.key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.key) // 采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, this.jwtExpirationDay);
        return c.getTime();
    }

    public String generateTokenWithUsername(String username){
        HashMap<String,Object> claims = new HashMap<>();
        claims.put(KEY_USERNAME,username);
        return generateToken(claims);
    }

    public String getUsernameFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        if(claims!=null){
            return claims.get(KEY_USERNAME)==null? null :claims.get(KEY_USERNAME).toString();
        }
        else{
            return  null;
        }
    }



}