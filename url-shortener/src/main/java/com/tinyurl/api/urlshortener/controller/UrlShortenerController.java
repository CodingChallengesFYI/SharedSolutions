package com.tinyurl.api.urlshortener.controller;

import com.tinyurl.api.urlshortener.model.UrlDTO;
import com.tinyurl.api.urlshortener.model.UrlRequestModel;
import com.tinyurl.api.urlshortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;
    

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }


    @PostMapping(
            value = "/shorten",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<UrlDTO> shortenUrl(@Valid @RequestBody UrlRequestModel urlRequestModel) {

        UrlDTO urlDTO =  urlShortenerService.shortenUrl(urlRequestModel.getLongUrl());
        HttpStatus status;
        if (!urlDTO.isNew()) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(urlDTO, status);
    }

    @GetMapping(
            value = "/{shortUrl}"
    )
    public ResponseEntity<?> doRedirect(@PathVariable(name="shortUrl") String shortUrl) {

        // TODO: implement actual redirection logic
        String longUrl = urlShortenerService.getLongUrl(shortUrl);

        if (longUrl != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", longUrl);

            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("URL not found", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(
            value = "/{shortUrl}"
    )
    public ResponseEntity<Boolean> deleteShortUrl(@PathVariable(name="shortUrl") String shortUrl) {

        return new ResponseEntity<>(urlShortenerService.deleteShortUrl(shortUrl), HttpStatus.OK);
    }

}
