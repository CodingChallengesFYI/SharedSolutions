package com.tinyurl.api.urlshortener.util;


import com.tinyurl.api.urlshortener.model.UrlDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UrlUtil {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Logger log = LoggerFactory.getLogger(UrlUtil.class);

    @Value(value = "${tinyurl.api.url}")
    private String url;

    public UrlDTO generateShortUrl(String longUrl) {
        if (longUrl == null || longUrl.isEmpty()) {
            throw new IllegalArgumentException("Long URL cannot be null or empty");
        }
        String md5Hash = generateMD5(longUrl.getBytes());

        String shortUrl = md5ToBase62(md5Hash).substring(0, 7);

        var urlDto = new UrlDTO(shortUrl, longUrl, url+shortUrl, true);

        log.info("Generated short URL for {}: {}", longUrl, shortUrl);
        return urlDto;
    }

    private String generateMD5(byte[] urlBytes) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(urlBytes);
            return new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    private String md5ToBase62(String md5Hash) {
        BigInteger bigInt = new BigInteger(md5Hash, 16);
        StringBuilder base62 = new StringBuilder();

        while(bigInt.compareTo(BigInteger.ZERO) > 0) {
           BigInteger[] divmod = bigInt.divideAndRemainder(BigInteger.valueOf(62));
            base62.append(BASE62.charAt(divmod[1].intValue()));
           bigInt = divmod[0];
        }

        return base62.reverse().toString();
    }

}
