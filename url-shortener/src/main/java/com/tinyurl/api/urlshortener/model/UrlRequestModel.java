package com.tinyurl.api.urlshortener.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UrlRequestModel {

    @NotNull
    @Pattern(
            regexp = "^(https?)://[^\\s/$.?#].[^\\s]*$",
            message = "Invalid URL format"
    )
    private String longUrl;

    public UrlRequestModel(){}

    public UrlRequestModel(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
