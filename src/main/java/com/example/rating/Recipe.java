package com.example.rating;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
@JsonIgnoreProperties
@Setter
@Builder
public class Recipe {

    private String urlFriendlyTitle;

    private Rating rating;
}
