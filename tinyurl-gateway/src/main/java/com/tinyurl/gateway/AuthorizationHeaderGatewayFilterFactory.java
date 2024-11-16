package com.tinyurl.gateway;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Component
public class AuthorizationHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizationHeaderGatewayFilterFactory.Config> {

    @Value("${jwt.token}")
    private String secretToken;

    public AuthorizationHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Authorization header missing", HttpStatus.UNAUTHORIZED);
            }

            String authorization = Objects.requireNonNull(request.getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);

            String jwt = authorization.replace("Bearer ", "").trim();

            if (!isValidJwt(jwt)){
                return onError(exchange, "Invalid JWT", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    private boolean isValidJwt(String jwt) {
        try {
            byte[] secretTokenBytes = secretToken.getBytes();
            SecretKey signingKey = Keys.hmacShaKeyFor(secretTokenBytes);

            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(jwt);

            String subject = jws.getPayload().getSubject();

            return subject != null && !subject.isEmpty();
        }catch(JwtException ex) {
            return false;
        }
    }

    public static class Config {

        private MessageDigest messageDigest;

        public MessageDigest getMessageDigest() {
            return messageDigest;
        }

        public void setAlgorithm(String algorithm) throws NoSuchAlgorithmException {
            messageDigest = MessageDigest.getInstance(algorithm);
        }
    }
}
