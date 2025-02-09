package com.example.rating;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;


    @PostMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveRatings() throws ExecutionException, InterruptedException, JsonProcessingException {
        ratingService.fetchAndSaveRatings();
        return ResponseEntity.ok("Ratings fetched and saved successfully!");
    }
}
