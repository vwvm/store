package org.vwvm.areamanagementfunction.adminServer.commons.utils;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>admin-server</h3>
 * <p>
 *
 * </p>
 *
 * @author : BlackBox
 * @date : 2023-02-22 10:59
 **/
public class JwtUtils {
    //@Value("{jwt.skin.key}")
    //key的大小必须大于或等于256bit,需要32位英文字符，一个英文字符为：8bit,一个中文字符为12bit
    private static final String key = "blackboxvwvmzjhfasfdssdfsfsfssfs";
    //设置加密算法
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    //获取header中的数据
    private static final Integer GET_HEADER_DATA = 0;
    //获取payload中的数据
    private static final Integer GET_PAYLOAD_DATA = 1;

    /**
     * 获取转换后的私钥对象
     *
     * @return
     */
    public static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    /**
     * 生成JWT
     *
     * @param exp     指定过期时间，不能小于当前时间
     * @param payLoad 携带的数据
     * @return jwp字符串
     */
    public static String createJwt(Date exp, Map<String, Object> payLoad) {
        return Jwts.builder()
                .setClaims(payLoad)//设置携带参数
                .setIssuedAt(new Date(System.currentTimeMillis()))//创建时间
                .setExpiration(exp)//过期时间
                .signWith(getSecretKey(), signatureAlgorithm)//设置加密算法和私钥
                .compact();
    }

    /**
     * 生成jwp
     *
     * @param payLoad 携带数据
     * @return jwp字符串
     */
    public static String createJwt(Map<String, Object> payLoad) {
        Date exp = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000);
        return createJwt(exp, payLoad);
    }

    /**
     * 解析JWS，返回一个布尔结果
     *
     * @param jwsString
     * @return
     */
    public static int parseJwt(String jwsString) {
        int temp;
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())//设置私钥
                    .build()
                    .parseClaimsJws(jwsString);//要解析的jws
            temp = 0;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT过期：");
            System.out.println(e.getMessage());
            temp = 1;
        } catch (UnsupportedJwtException e) {
            System.out.println("不支持的JWT：");
            System.out.println(e.getMessage());
            temp = 2;
        } catch (MalformedJwtException e) {
            System.out.println("JWT格式错误：");
            System.out.println(e.getMessage());
            temp = 3;
        } catch (IllegalArgumentException e) {
            System.out.println("非法请求：");
            System.out.println(e.getMessage());
            temp = 4;
        } catch (Exception e) {
            System.out.println("解析异常：");
            System.out.println(e.getMessage());
            temp = 5;
        }
        return temp;
    }


    public static String getJson(String jwsString, Integer code) {
        //判断解析结果如果失败返回空，如果有全局异常处理，此处可抛自定义异常进行处理
        if (!(parseJwt(jwsString) == 0)) return null;
        //将jws中的数据编码串截取出来使用Base64解析成字节数组
        byte[] decodePayLoad = Base64Utils.decodeFromString(jwsString.split("\\.")[code]);
        return new String(decodePayLoad);
    }

    public static Map<String, Object> getData(String jwsString, Integer code) {
        //此处使用的阿里的fastJson,可使用其他的工具将字节json字节转map
        return JSON.parseObject(getJson(jwsString, code), Map.class);
    }

    /**
     * 获取header中的数据
     *
     * @param jwsString
     * @return
     */
    public static Map<String, Object> getHeader(String jwsString) {
        return getData(jwsString, GET_HEADER_DATA);
    }

    /**
     * 获取PayLoad中携带的数据
     *
     * @param jwsString
     * @return
     */
    public static Map<String, Object> getPayLoad(String jwsString) {
        return getData(jwsString, GET_PAYLOAD_DATA);
    }

    /**
     * 获取除去exp和iat的数据，exp：过期时间，iat：JWT生成的时间
     *
     * @param jwsString
     * @return
     */
    public static Map<String, Object> getPayLoadALSOExcludeExpAndIat(String jwsString) {
        Map<String, Object> map = getData(jwsString, GET_PAYLOAD_DATA);
        map.remove("exp");
        map.remove("iat");
        return map;
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        //创建一个过期时间
        Date date = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000);
        //设置要携带的数据
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        //创建工具类对象
        JwtUtils jwtUtils = new JwtUtils();
        String jwt = jwtUtils.createJwt(date, map);
        System.out.println(jwt);
        jwtUtils.parseJwt(jwt);
        System.out.println(jwtUtils.getPayLoad(jwt));
    }

}

