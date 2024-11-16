package com.tinyurl.api.urlshortener.model;

public class UrlDTO {

    private String key;
    private String longUrl;
    private String shortUrl;
    private boolean isNew;

    public UrlDTO(String key, String longUrl, String shortUrl, boolean isNew) {
        this.key = key;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.isNew = isNew;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public UrlDTO() {
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
