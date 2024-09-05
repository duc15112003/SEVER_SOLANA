package com.solana.com.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Component
public class JwtUtil {
    // Khóa bí mật dùng để mã hóa và giải mã JWT
    private String secret = "c9tN1u5MJkUuhqg8ml+CLqYtWsZfGxwjVZX+DVIAIipJvYuLomZGarmDsgt0ykD7";

    // Thời gian sống của Access Token: 10 phút
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 10;

    // Thời gian sống của Refresh Token: 7 ngày
    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    // Tạo Access Token từ UserDetails
    public String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername(), ACCESS_TOKEN_EXPIRATION);
    }

    // Tạo Refresh Token từ UserDetails
    public String generateRefreshToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername(), REFRESH_TOKEN_EXPIRATION);
    }

    // Tạo token với thời gian hết hạn cụ thể
    private String createToken(String subject, long expirationTime) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(secret))
                .compact();
    }

    // Xác thực token dựa trên thông tin người dùng
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Trích xuất tên người dùng từ token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Trích xuất ngày hết hạn từ token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Trích xuất các claim từ token dựa trên hàm giải quyết claim
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Trích xuất tất cả các claims từ token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(secret))
                .parseClaimsJws(token)
                .getBody();
    }

    // Kiểm tra xem token đã hết hạn chưa
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
