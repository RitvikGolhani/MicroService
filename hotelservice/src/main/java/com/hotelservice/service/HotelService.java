package com.hotelservice.service;

import com.hotelservice.entity.Hotel;
import com.hotelservice.exception.ResourceNotFoundException;
import com.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService {
    @Autowired
    private HotelRepository  hotelRepository;

    public Hotel saveHotel(Hotel hotel) {
        final String string = UUID.randomUUID().toString();
        hotel.setHotelId(string);
        return hotelRepository.save(hotel);
    }

    public List getAllHotel() {
        return hotelRepository.findAll();
    }

    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel is not found with id "+hotelId));

    }

    public Hotel deleteHotel(String hotelId) {
         Hotel hotel = getHotel(hotelId);
         hotelRepository.deleteById(hotelId);
         return hotel;
    }
}
