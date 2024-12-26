package com.example.demo.controller;

import com.example.demo.model.HotelRoom;
import com.example.demo.services.HotelRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class HotelRoomController {
    private final HotelRoomService service;

    public HotelRoomController(HotelRoomService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllRooms(Model model) {
        List<HotelRoom> rooms = service.getAllRooms();
        model.addAttribute("rooms", rooms); // Gửi danh sách phòng đến view
        model.addAttribute("room", new HotelRoom()); // Đối tượng phòng mới để hiển thị form thêm phòng
        return "rooms"; // Tên của view (rooms.html)
    }

    @PostMapping
    public String addRoom(@ModelAttribute HotelRoom room) {
        service.addRoom(room); // Thêm phòng vào database
        return "redirect:/rooms"; // Sau khi thêm phòng, redirect lại trang danh sách phòng
    }

    @PutMapping("/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute HotelRoom updatedRoom) {
        service.updateRoom(id, updatedRoom);
        return "redirect:/rooms"; // Cập nhật xong, redirect lại trang danh sách
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String deleteRoom(@PathVariable Long id, @RequestParam("_method") String method) {
        if ("DELETE".equals(method)) {
            service.deleteRoom(id); // Gọi service để xóa phòng
        }
        return "redirect:/rooms"; // Sau khi xóa, redirect lại trang danh sách phòng
    }
}
