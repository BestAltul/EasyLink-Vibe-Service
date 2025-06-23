package com.easylink.vibe_service.infrastructure.security;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtAuthFilter implements Filter {

    private PublicKey publicKey;

    @PostConstruct
    public void loadPublicKey() throws Exception{
        InputStream inputStream = getClass().getResourceAsStream("/keys/public.pem");
        String key = new String(inputStream.readAllBytes())
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        publicKey = KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader!= null && authHeader.startsWith("Bearer")){
            String token = authHeader.substring(7);
            try{
                Jwts.parserBuilder()
                        .setSigningKey(publicKey)
                        .build()
                        .parseClaimsJws(token);
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }catch (JwtException e){
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Missing token");
    }
}
