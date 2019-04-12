package com.itemsSharing.jwt;


import com.itemsSharing.config.ApiParameters;
import org.springframework.beans.factory.annotation.Value;

public class JwtTokenAuthenticationConfig {

    @Value("${jwt.url:/login}")
    private String url = "/user";

    @Value("${jwt.header}")
    private String header="Authorization";

    @Value("${jwt.prefix}")
    private String prefix="Bearer";

    @Value("${jwt.expiration}")
    private int expiration=28800; // default 8 hours

    @Value(ApiParameters.JWT_SECRET)
    private String secret="dsflkakw0893209rpoiefjslkdljrsf0980epqdsa";

    public JwtTokenAuthenticationConfig() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}