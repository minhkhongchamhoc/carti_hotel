package com.example.demo.services;

import com.example.demo.controller.HotelRoomController;
import com.example.demo.model.HotelRoom;
import com.example.demo.repository.HotelRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRoomService {
    private final HotelRoomRepository repository;


    public HotelRoomService(HotelRoomRepository repository) {
        this.repository = repository;
    }

    public List<HotelRoom> getAllRooms(){
        return repository.findAll();
    }

    public HotelRoom addRoom(HotelRoom room ){
        return repository.save(room);
    }

    public HotelRoom updateRoom(Long id, HotelRoom updatedRoom) {
        return repository.findById(id)
                .map(room -> {
                    room.setName(updatedRoom.getName());
                    room.setType(updatedRoom.getType());
                    room.setPrice(updatedRoom.getPrice());
                    room.setAvailable(updatedRoom.isAvailable());
                    return repository.save(room);
                })
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }


    // Xóa phòng theo ID
    public void deleteRoom(Long id) {
        repository.deleteById(id);
    }

}
