package com.example.rating;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.System.exit;

@Service
@RequiredArgsConstructor
public class RatingService {
    private static final String FEED_URL = "https://realfood.tesco.com/api/feed/dataextract?authkey=fd2693ee-494e-40bd-8600-576d59293d46";

    private final RatingRepository ratingRepository;

    public void fetchAndSaveRatings() throws InterruptedException, ExecutionException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        CompletableFuture<ResponseEntity<String>> responseFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return restTemplate.getForEntity(FEED_URL, String.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch data", e);
            }
        });

        // Wait for the response asynchronously
        ResponseEntity<String> response = responseFuture.get();

        if (response.getStatusCode() == HttpStatus.OK) {
            final String responseBody = response.getBody();

            // Parse JSON response using Jackson's streaming API for efficiency
            final ObjectMapper objectMapper = new ObjectMapper();
            final List<Rating> ratings = new ArrayList<>();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            // Iterate over each field in the root node
            rootNode.fields().forEachRemaining(entry -> {
                String key = entry.getKey(); // Key of the current field (if needed)
                JsonNode recipeNode = entry.getValue(); // Value (the actual object)


                // If the entry itself is an array, iterate through its elements
                if (recipeNode.isArray()) {
                    for (JsonNode recipe : recipeNode) {

                        // Check for the outputRecipe and its contents
                        if (recipe.has("outputRecipe")) {
                            JsonNode outputRecipe = recipe.get("outputRecipe");

                            // If outputRecipe is an array, process each element

                                    // Extract urlFriendlyTitle and rating
                                    String urlFriendlyTitle = outputRecipe.has("urlFriendlyTitle") ? outputRecipe.get("urlFriendlyTitle").asText() : null;
                                    JsonNode ratingNode = outputRecipe.get("rating");

                                    if (ratingNode != null && ratingNode.has("average")) {
                                        Double averageRating = ratingNode.get("average").asDouble();

                                        // Validate urlFriendlyTitle and averageRating before processing
                                        if (urlFriendlyTitle != null && !urlFriendlyTitle.trim().isEmpty() && averageRating != null) {
                                            Rating rating = new Rating().builder()
                                                    .urlFriendlyTitle(urlFriendlyTitle)
                                                    .currentDay(LocalDate.now())
                                                    .averageRating(String.valueOf(averageRating))
                                                    .build();

                                            // Save the Rating entity to the repository
                                            ratings.add(rating);
                                        } else {
                                            System.out.println("Skipping outputRecipe with invalid data: " + outputRecipe);
                                        }
                                    }
                                }

                    }
                } else {
                    System.out.println("Entry is not an array: " + recipeNode);
                }
            });


            // Save ratings in batches to avoid individual DB calls for each entry
            batchSaveRatings(ratings);
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.getStatusCode());
        }
    }

    // Optimized batch save method
    public void batchSaveRatings(List<Rating> ratings) {
        if (!ratings.isEmpty()) {
            // Assuming RatingRepository is set up for batch inserts
            ratingRepository.saveAll(ratings); // Save all ratings at once (assuming it supports batch save)
        }
    }
}
