package com.userservice.external.services;

import com.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRating(@PathVariable String userId);


    @PostMapping("/ratings")
     Rating createRating(@RequestBody Rating rating);

    @DeleteMapping("ratings/{ratingId}")
    Rating deleteRating(@PathVariable String ratingId);

}
