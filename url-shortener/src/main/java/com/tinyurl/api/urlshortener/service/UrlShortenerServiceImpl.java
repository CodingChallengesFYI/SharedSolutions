package com.tinyurl.api.urlshortener.service;

import com.tinyurl.api.urlshortener.model.UrlDTO;
import com.tinyurl.api.urlshortener.util.UrlUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService{


    private static final Logger log = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);
    private final UrlUtil urlUtil;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${spring.redis.shorten_prefix}")
    private String longToShortPrefix;

    @Value("${spring.redis.redirect_prefix}")
    private String redirectPrefix;

    @Autowired
    public UrlShortenerServiceImpl(UrlUtil urlUtil, RedisTemplate<String, String> redisTemplate) {
        this.urlUtil = urlUtil;
        this.redisTemplate = redisTemplate;
    }

    public UrlDTO shortenUrl(String longUrl) {
        log.info("Shortening URL - Version 2: {}", longUrl);
        UrlDTO urlDTO;

        String shortUrl = redisTemplate.opsForValue().get(longToShortPrefix + longUrl);
        if (shortUrl == null) {
            urlDTO =  urlUtil.generateShortUrl(longUrl);

            redisTemplate.opsForValue().set(longToShortPrefix + longUrl, urlDTO.getShortUrl());
            redisTemplate.opsForValue().set(redirectPrefix + urlDTO.getKey(), longUrl);

        } else {
            var key = shortUrl.substring(shortUrl.length() - 7);

            urlDTO = new UrlDTO(key, longUrl, shortUrl, false);
        }
        return urlDTO;
    }

    @Override
    public String getLongUrl(String shortUrl) {
        log.info("Getting long URL for short URL: {}", shortUrl);

        return redisTemplate.opsForValue().get(redirectPrefix + shortUrl);
    }

    @Override
    public boolean deleteShortUrl(String shortUrl) {

        String longUrl = redisTemplate.opsForValue().get(redirectPrefix + shortUrl);
        if (longUrl == null) {
            return false;
        }

        redisTemplate.delete(redirectPrefix + shortUrl);

        return Boolean.TRUE.equals(redisTemplate.delete(longToShortPrefix + longUrl));
    }
}
