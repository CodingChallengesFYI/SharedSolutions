package com.tinyurl.api.urlshortener.service;

import com.tinyurl.api.urlshortener.model.UrlDTO;

public interface UrlShortenerService {

    UrlDTO shortenUrl(String longUrl);

    String getLongUrl(String shortUrl);

    boolean deleteShortUrl(String shortUrl);
}
