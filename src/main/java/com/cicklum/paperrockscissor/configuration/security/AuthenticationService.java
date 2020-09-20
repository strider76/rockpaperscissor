package com.cicklum.paperrockscissor.configuration.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.exception.AccessDeniedException;

@Service
public class AuthenticationService {

    //Token Expiration time (ms) 60 * 60 * 1000 = 3600000 1 hora
    private static final long EXPIRATIONTIME = 3600000L;
    private static final String PREFIX = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    // Key for Signing/Decode JWT
    @Value("${spring.app.jwtSecret}")
    private String signingkey;

    /**
     * Create JWT and we add it to header's response in Authorization Attribute
     * and finally we give access-Controll so JavaScript Can access to Authorization attribute and
     * could take  JWT
     * @param response
     * @param userName
     */
    public void addToken(HttpServletResponse response, String userName) {
        String jwt = Jwts.builder()
                        .setSubject(userName)
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                        .signWith(SignatureAlgorithm.HS512, signingkey)
                        .compact();
        response.addHeader(AUTHORIZATION, PREFIX + " " + jwt);
        response.addHeader("Access-Control-Expose-Headers", AUTHORIZATION);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "180");
    }



    /**
     * On the following call, we Obtain JWT across header in Authorization attribute
     * if token contains a user, then we authenticate the user in the app
     * @param request
     * @return
     * @throws AccessDeniedException
     */
    public Authentication getAuthentication (HttpServletRequest request) throws AccessDeniedException {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(signingkey)
                        .parseClaimsJws(token.replace(PREFIX, ""))
                        .getBody()
                        .getSubject();

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
                }
            } catch (ExpiredJwtException expired) {
                throw new AccessDeniedException("Token expired, please login again");
            } catch (SignatureException signature) {
                throw new AccessDeniedException("Invalid Token");
            }
        }
        return null;
    }
}
