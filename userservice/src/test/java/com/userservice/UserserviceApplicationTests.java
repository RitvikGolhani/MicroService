package com.userservice;

import com.userservice.entity.Rating;
import com.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserserviceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;
	@Test
	void createReating(){
		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("good").build();
		final Rating rating1 = ratingService.createRating(rating);
		System.out.println("created");
	}
	@Test
	void deleteRating() {
		try {
			// Perform the deletion and handle any exceptions
			ratingService.deleteRating("be786635-96ca-498b-a6fe-657c4dc7c969");
			System.out.println("Rating deleted successfully.");
		} catch (Exception e) {
			// Handle any exceptions that might occur during deletion
			System.err.println("Error deleting rating: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
