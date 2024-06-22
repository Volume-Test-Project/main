package com.volumeTest.volume.common.security.jwt;

import com.volumeTest.volume.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * JwtProvider 토큰을 생성하고 유효성을 검사하는 클래스
 */
@Component
@AllArgsConstructor
public class JwtProvider {

    JwtProperties jwtProperties;

    /**
     * 토큰 발급
     *
     * @param member    Member 객체
     * @param expiredAt 만료 시간
     * @return String
     */
    public String generateToken(Member member, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), member);
    }

    /**
     * 토큰 생성
     *
     * @param member Member 객체
     * @return String
     */
    private String makeToken(Date expirationDate, Member member) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //헤더 type: jwt
                .setIssuer(jwtProperties.getIssuer()) //발급자
                .setIssuedAt(new Date()) //발급 일자
                .setExpiration(expirationDate) //만료 일자
                .setSubject(member.getEmail()) //주제: 유저 이메일
                .claim("id", member.getMemberId()) //클레임 ID: 유저 ID
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * 토큰 유효성 검사
     *
     * @param token String
     * @return boolean
     */
    public boolean validToken(final String token) {
        try {
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build();
            return parser.parseClaimsJws(token).getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 토큰으로부터 Authentication 객체를 가져옴
     *
     * @param token String
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(
                new User(claims.getSubject(), "", authorities),
                token,
                authorities
        );
    }

    /**
     * 토큰으로부터 MemberId를 가져옴
     *
     * @param token String
     * @return Long
     */
    public Long getMemberId(String token) {
        return Long.parseLong(getClaims(token).get("id").toString());
    }

    /**
     * 토큰으로부터 Claims를 가져옴
     *
     * @param token String
     * @return Claims
     */
    private Claims getClaims(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
        return parser.parseClaimsJws(token).getBody();
    }

    /**
     * 서명 키 생성
     *
     * @return SecretKey
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }
}
