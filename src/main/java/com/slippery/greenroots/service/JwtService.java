package com.slippery.greenroots.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Configuration
public class JwtService {
    private final Long EXPIRATION_TIME =864000L;
    private final String SECRET_STRING="8bf12d7553103711188a0899404f34c672fa6a15044bdcb195b9faf316f694c64cf731927e3ab3058f0b846d9d3b0849f74c26cdcadb4afc2995376787646e8bb0b8f3b48bd73922c1150b72edf402e4f43da5da2eff4626189bfcf7fa97e71e42dbf0648c537d8b3fbf1fe6e99086125769ae4c19240ca80c0f4e42f93f340691e01338a5bdfd590611f10cae05f4a475ba287b64006a9676973d3cd30f76c517939adfc8d96ebf0628622aa721f2e59b3e46e35595c6583c6eb7e709debb9aa0cfc8f75d85f0cfadc186eeff42af37cc38e1beb1bc5848e4e4295131d3944a7ea480d5ba1442dd85690f5888702c39053c2f1929c33a5a660f130618e1901e";

    protected SecretKey generateSecretKey(){
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_STRING);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateJwtToken(String username){
        Map<String,Object> claims=new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .and()
                .signWith(generateSecretKey())
                .compact();
    }

}
