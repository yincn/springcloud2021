package com.example.util;

import com.example.req.UserLoginReq;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@Slf4j
public class TokenEncryptUtil {

    //签名秘钥
    private static final String BASE64SECRET = "eyJzdWIiOiI2MzY3Nzk3MjcyNTA4IiwiTE9HSU5fS0VZfQ==";

    //设置过期时间为1个月
    private static final long EXPIRE_TIME = 2592000000L;

    /**
     * 生成Token
     * @return
     */
    public static String encrypt(UserLoginReq req) {
        try {
            /**
             * 1.创建签名秘钥
             */
            MACSigner macSigner = new MACSigner(DatatypeConverter.parseBase64Binary(BASE64SECRET));
            /**
             * 2. 建立payload 载体
             */
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("username", req.getUsername())
                    //.claim("appUserid", loginRes.getAppUserId())
                    .build();

            /**
             * 3. 建立签名
             */
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(macSigner);

            /**
             * 4. 生成token
             */
            return signedJWT.serialize();
        } catch (Exception e) {
            log.error("message:{}", e.getMessage());
        }
        return null;
    }
}