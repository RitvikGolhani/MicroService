package com.ratingservice.service;

import com.ratingservice.entity.Rating;
import com.ratingservice.exception.ResourceNotFoundException;
import com.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService  {
    @Autowired
    private RatingRepository ratingRepository;

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List getAllRating() {
        return ratingRepository.findAll();
    }

    public Rating getRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating is not found with id "+ratingId));

    }

    public void deleteRating(String ratingId) {
        Rating hotel = getRating(ratingId);
         ratingRepository.deleteById(ratingId);

    }


    public List<Rating> findByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }


    public List<Rating> findByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
