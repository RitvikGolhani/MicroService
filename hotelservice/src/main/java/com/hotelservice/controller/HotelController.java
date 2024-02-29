package com.hotelservice.controller;

import com.hotelservice.entity.Hotel;
import com.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @PreAuthorize("hasAuthority('Admin')")

    @PostMapping
    public ResponseEntity<?> saveHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(hotel1, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')||hasAuthority('Admin')")

    @GetMapping
    public ResponseEntity<?> getAllHotel(){
        List list = hotelService.getAllHotel();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("{hotelId}")
    public ResponseEntity<?> getUser(@PathVariable String hotelId){
        Hotel hotel=hotelService.getHotel(hotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @DeleteMapping("{hotelId}")
    public ResponseEntity<?> delteUser(@PathVariable String hotelId){
        Hotel hotel= hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>("Hotel successfully deleted" + hotel, HttpStatus.OK);

    }
}
