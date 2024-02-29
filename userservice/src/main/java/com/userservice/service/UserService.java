package com.userservice.service;

import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.external.services.HotelService;
import com.userservice.external.services.RatingService;
import com.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserRepository userRepository;
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
         List<User> all = userRepository.findAll();
         for(User user:all){
             final List<Rating> ratings = ratingService.getRating(user.getUserId());
             final List<Rating> ratingList = ratings.stream().map(rating->{
                 final Hotel body = hotelService.getHotel(rating.getHotelId());
                 rating.setHotel(body);
                 return rating;
             }).collect(Collectors.toList());
             user.setRating(ratingList);

         }
        return all;
    }

    public User getUser(String userId) {

        User user =userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with user id "+userId));
        final List<Rating> ratings = ratingService.getRating(user.getUserId());
        final List<Rating> ratingList = ratings.stream().map(rating->{
            final Hotel body = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(body);
            return rating;
        }).collect(Collectors.toList());
        user.setRating(ratingList);
        return user;
    }

    public User deleteUser(String userId) {
         User user = getUser(userId);
        userRepository.deleteById(userId);
        return user;
    }
}












// RESTTEMPLATE LOGIC
//public List<User> getAllUser() {
//    List<User> all = userRepository.findAll();
//    for(User user:all){
//        Rating[] forObject = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
//        logger.info("{}",forObject);
//        final List<Rating> ratings = Arrays.stream(forObject).toList();
//        final List<Rating> ratingList = ratings.stream().map(rating->{
////         http://localhost:8082/hotels/1e8f58d5-0114-401d-be76-666d583752bd
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            final Hotel body = forEntity.getBody();
//            rating.setHotel(body);
//            return rating;
//        }).collect(Collectors.toList());
//        user.setRating(ratingList);
//
//    }
//    return all;
//}

//public User getUser(String userId) {

//    User user =userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with user id "+userId));
////   http://localhost:8083/ratings/users/3
//    Rating[] forObject = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
//    logger.info("{}",forObject);
//    final List<Rating> ratings = Arrays.stream(forObject).toList();
//    final List<Rating> ratingList = ratings.stream().map(rating->{
////         http://localhost:8082/hotels/1e8f58d5-0114-401d-be76-666d583752bd
//        ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//        final Hotel body = forEntity.getBody();
//        rating.setHotel(body);
//        return rating;
//    }).collect(Collectors.toList());
//    user.setRating(ratingList);
//
//    return user;
//}