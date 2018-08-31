package com.zero.customer.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zero.common.constants.SystemConstants;
import com.zero.common.util.DateHelper;
import com.zero.common.util.JsonHelper;
import com.zero.user.po.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2018/8/17
 */
public class JwtTokenUtil implements Serializable {

    private static final String JWT_ID = "customer";
    private static final TypeReference<Map<String, Object>> TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };
    private static byte[] encodedKey = Base64.decode(SystemConstants.JWT_SECRET);
    private static SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

    public static String generateJwt(User user, int ttlMillis) {
        Map<String, Object> map = new HashMap<>(10);
        map.put("userId", user.getId());
        map.put("name", user.getName());
        map.put("phone", user.getPhone());
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        JwtBuilder builder = Jwts.builder().setId(JWT_ID).setSubject(JsonHelper.toJSon(map)).setIssuedAt(now)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            builder.setExpiration(DateHelper.addSecond(now, ttlMillis));
        }
        return builder.compact();
    }

    public static String generateJwt(String subject, int ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        JwtBuilder builder = Jwts.builder().setId(JWT_ID).setSubject(subject).setIssuedAt(now)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            builder.setExpiration(DateHelper.addSecond(now, ttlMillis));
        }
        return builder.compact();
    }

    public static Claims parseJWT(String token) throws Exception {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    /**
     * 还原属性
     */
    public static Map<String, Object> parseMaps(String token) throws Exception {
        return JsonHelper.readValue(parseJWT(token).getSubject(), TYPE_REFERENCE);
    }

    /**
     * 获得属性
     */
    public static Object parseProperty(String token, String filed) throws Exception {
        return parseMaps(token).get(filed);
    }

    /**
     * 获取用户id
     */
    public static Integer parseUserId(String token) throws Exception {
        return (Integer) parseProperty(token, "userId");
    }

    public static Date validateToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public static String refreshToken(String token) throws Exception {
        return generateJwt(JsonHelper.toJSon(parseMaps(token)), SystemConstants.JWT_TTL_MILLIS);
    }
}
