package com.ratingservice.controller;

import com.ratingservice.entity.Rating;
import com.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController{

    @Autowired
    private RatingService ratingService;
    @PreAuthorize("hasAuthority('Admin')")

    @PostMapping
    public ResponseEntity<?> saveRating(@RequestBody Rating rating){
        Rating savedRating = ratingService.saveRating(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')||hasAuthority('Admin')")

    @GetMapping
    public ResponseEntity<?> getAllRating(){
        List list = ratingService.getAllRating();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')")

    @GetMapping("{ratingId}")
    public ResponseEntity<?> getRating(@PathVariable String ratingId){
        Rating rating=ratingService.getRating(ratingId);
        return new ResponseEntity<>(rating, HttpStatus.OK);

    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getRatingByUserId(@PathVariable String userId){
        final List<Rating> byUserId = ratingService.findByUserId(userId);
        return new ResponseEntity<>(byUserId, HttpStatus.OK);

    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getRatingByHotelId(@PathVariable String hotelId){
        final List<Rating> byHotelId = ratingService.findByHotelId(hotelId);
        return new ResponseEntity<>(byHotelId, HttpStatus.OK);

    }


    @DeleteMapping("{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable String ratingId){
       ratingService.deleteRating(ratingId);
        return new ResponseEntity<>("Hotel successfully deleted" , HttpStatus.OK);
    }

}
